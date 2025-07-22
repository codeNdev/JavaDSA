package game;

public class AdaptiveEnsembleBot implements Strategy {
    private static final String ROCK     = "rock";
    private static final String PAPER    = "paper";
    private static final String SCISSORS = "scissors";
    private static final String FOLD     = "fold";

    private static final double EPSILON = 0.05;
    private static final int MAX_HANDS = 5;

    public String throwHand(String[] player,
                            String[] opponent,
                            int handNumber) throws Exception {
        try {
            int n = handNumber - 1;  // moves already played

            // 1) On first move, pick a random normal hand
            if (n == 0) {
                return pickRandomNormal();
            }

            // 2) Recompute transition counts and global frequencies
            int[][] trans1   = new int[4][4];
            int[][][] trans2 = new int[4][4][4];
            int[] freq       = new int[4];

            for (int i = 0; i < n; i++) {
                int cur = idx(opponent[i]);
                freq[cur]++;
                if (i > 0) {
                    int prev = idx(opponent[i - 1]);
                    trans1[prev][cur]++;
                }
                if (i > 1) {
                    int prev2 = idx(opponent[i - 2]);
                    int prev1 = idx(opponent[i - 1]);
                    trans2[prev2][prev1][cur]++;
                }
            }

            // 3) Build opponent next‐move distribution
            double[] p = new double[4];
            int last1 = idx(opponent[n - 1]);
            int last2 = (n > 1 ? idx(opponent[n - 2]) : -1);

            // try second‐order Markov
            int total2 = 0;
            if (n > 1) {
                for (int j = 0; j < 4; j++) {
                    total2 += trans2[last2][last1][j];
                }
            }
            if (total2 > 0) {
                for (int j = 0; j < 4; j++) {
                    p[j] = trans2[last2][last1][j] / (double) total2;
                }
            }
            // fallback to first‐order
            else {
                int total1 = 0;
                for (int j = 0; j < 4; j++) {
                    total1 += trans1[last1][j];
                }
                if (total1 > 0) {
                    for (int j = 0; j < 4; j++) {
                        p[j] = trans1[last1][j] / (double) total1;
                    }
                }
                // fallback to global frequency
                else {
                    for (int j = 0; j < 4; j++) {
                        p[j] = freq[j] / (double) n;
                    }
                    // if freq all zero (shouldn’t happen when n>0), uniform
                    if (n == 0) {
                        for (int j = 0; j < 4; j++) {
                            p[j] = 0.25;
                        }
                    }
                }
            }

            // 4) Compute expected values
            // win=5, tie=3, loss=0 vs normal; vs fold we get 4
            double expRock     = 5 * p[2] + 3 * p[0] + 0 * p[1] + 4 * p[3];
            double expPaper    = 5 * p[0] + 3 * p[1] + 0 * p[2] + 4 * p[3];
            double expScissors = 5 * p[1] + 3 * p[2] + 0 * p[0] + 4 * p[3];
            double bestExpNorm = Math.max(expRock,
                    Math.max(expPaper, expScissors));

            // 5) Simulate current round score
            int scoreMe = 0, scoreOp = 0;
            for (int i = 0; i < n; i++) {
                String me  = player[i];
                String you = opponent[i];

                if (me.equals(FOLD)) {
                    scoreMe  += 3;
                    scoreOp  += you.equals(FOLD) ? 3 : 4;
                }
                else if (you.equals(FOLD)) {
                    scoreMe  += 4;
                    scoreOp  += 3;
                }
                else if (me.equals(you)) {
                    scoreMe += 3;
                    scoreOp += 3;
                }
                else if ((me.equals(ROCK) && you.equals(SCISSORS)) ||
                        (me.equals(PAPER) && you.equals(ROCK))   ||
                        (me.equals(SCISSORS) && you.equals(PAPER))) {
                    scoreMe += 5;
                } else {
                    scoreOp += 5;
                }
            }

            int remaining = MAX_HANDS - n;

            // 6) Last‐hand aggressiveness if we’re trailing
            if (remaining == 1 && scoreMe < scoreOp) {
                // never fold when desperate
                if (expRock >= expPaper && expRock >= expScissors) {
                    return ROCK;
                }
                if (expPaper >= expScissors) {
                    return PAPER;
                }
                return SCISSORS;
            }

            // 7) Standard decide: fold if no normal hand beats 3 pts
            if (bestExpNorm <= 3.0) {
                // occasional exploration
                if (Math.random() < EPSILON) {
                    return randomMove();
                }
                return FOLD;
            }

            // 8) ε-greedy on normal actions
            if (Math.random() < EPSILON) {
                return randomMove();
            }
            if (bestExpNorm == expRock) {
                return ROCK;
            }
            if (bestExpNorm == expPaper) {
                return PAPER;
            }
            return SCISSORS;
        }
        catch (Exception e) {
            throw new Exception(this.getClass().getSimpleName());
        }
    }

    // map a move string to 0..3
    private int idx(String m) {
        switch (m) {
            case ROCK:     return 0;
            case PAPER:    return 1;
            case SCISSORS: return 2;
            case FOLD:     return 3;
            default:       return -1;
        }
    }

    // pick one of rock/paper/scissors at random
    private String pickRandomNormal() {
        String[] normal = {ROCK, PAPER, SCISSORS};
        return normal[(int) (Math.random() * 3)];
    }

    // pick any of the four moves at random
    private String randomMove() {
        String[] all = {ROCK, PAPER, SCISSORS, FOLD};
        return all[(int) (Math.random() * 4)];
    }
}
