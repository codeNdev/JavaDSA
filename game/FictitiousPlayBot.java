package game;

import java.util.Arrays;
import java.util.Random;

public class FictitiousPlayBot implements Strategy {
    private static final String[] HANDS = {"rock", "paper", "scissors", "fold"};
    private static final int R = 0, P = 1, S = 2, F = 3;
    private int[] oppCounts = new int[4];
    private Random rand = new Random();
    // With small probability we explore to avoid cycles
    private static final double EPSILON = 0.05;

    @Override
    public String throwHand(String[] player, String[] opponent, int handNumber) throws Exception {
        try {
            // 1) Update opponent’s count of the *last* play
            if (handNumber > 1) {
                String lastOpp = opponent[handNumber - 2];
                oppCounts[index(lastOpp)]++;
            }

            int total = Arrays.stream(oppCounts).sum();

            // 2) If no history yet, just pick “rock”
            if (total == 0) {
                return "rock";
            }

            // 3) ε‐greedy: with small probability, pick completely at random
            if (rand.nextDouble() < EPSILON) {
                return HANDS[rand.nextInt(4)];
            }

            // 4) Otherwise compute expected payoff for each candidate move
            String bestMove = null;
            double bestScore = Double.NEGATIVE_INFINITY;
            for (String mine : HANDS) {
                double expScore = 0;
                for (int j = 0; j < 4; j++) {
                    String opp = HANDS[j];
                    double prob = (double) oppCounts[j] / total;
                    expScore += prob * payoff(mine, opp);
                }
                if (expScore > bestScore) {
                    bestScore = expScore;
                    bestMove = mine;
                }
            }

            return bestMove;
        }
        catch (Exception e) {
            // Force disqualification if anything unexpected happens
            throw new Exception(this.getClass().getSimpleName());
        }
    }

    // Map move→index
    private int index(String s) {
        switch (s) {
            case "rock":     return R;
            case "paper":    return P;
            case "scissors": return S;
            case "fold":     return F;
            default:         return -1;
        }
    }

    // Payoff matrix as per rules:
    // win=5, tie=3, lose=0; fold vs anything=3; normal vs fold=4
    private int payoff(String me, String opp) {
        if (me.equals("fold")) {
            return 3;
        }
        if (opp.equals("fold")) {
            return 4;
        }
        if (me.equals(opp)) {
            return 3;
        }
        // rock beats scissors, paper beats rock, scissors beats paper
        if ((me.equals("rock")     && opp.equals("scissors")) ||
                (me.equals("paper")    && opp.equals("rock"))     ||
                (me.equals("scissors") && opp.equals("paper"))) {
            return 5;
        }
        return 0;
    }
}

