package game;

// GameController.java
import java.util.Arrays;

public class GameController {
    private static final int HANDS_PER_ROUND   = 30;
    private static final int ROUNDS_PER_MATCH  = 100;
    private static final int ROUNDS_TO_WIN     =16;

    public static void main(String[] args) {
        Strategy bot1 = new DragonBot();
        Strategy bot2 = new AdaptiveEnsembleBot();
        playMatch(bot1, bot2);
    }

    private static void playMatch(Strategy bot1, Strategy bot2) {
        int wins1 = 0, wins2 = 0;

        for (int round = 1;
             round <= ROUNDS_PER_MATCH
                     && wins1 < ROUNDS_TO_WIN
                     && wins2 < ROUNDS_TO_WIN;
             round++) {

            System.out.println("\n=== Round " + round + " ===");
            String[] h1 = new String[HANDS_PER_ROUND];
            String[] h2 = new String[HANDS_PER_ROUND];
            int s1 = 0, s2 = 0;
            boolean dq1 = false, dq2 = false;

            for (int hand = 1; hand <= HANDS_PER_ROUND; hand++) {
                String[] p1 = Arrays.copyOf(h1, hand - 1);
                String[] p2 = Arrays.copyOf(h2, hand - 1);
                String m1, m2;

                try {
                    m1 = bot1.throwHand(p1, p2, hand);
                } catch (Exception e) {
                    dq1 = true;
                    System.out.println("DragonBot disqualified on hand " + hand);
                    break;
                }
                try {
                    m2 = bot2.throwHand(p2, p1, hand);
                } catch (Exception e) {
                    dq2 = true;
                    System.out.println("FictitiousPlayBot disqualified on hand " + hand);
                    break;
                }

                if (!valid(m1)) { dq1 = true; break; }
                if (!valid(m2)) { dq2 = true; break; }

                h1[hand - 1] = m1;
                h2[hand - 1] = m2;

                int[] sc = scoreHand(m1, m2);
                s1 += sc[0];
                s2 += sc[1];
                System.out.printf(" Hand %d: DragonBot=%s  FictPlayBot=%s  => %d–%d%n",
                        hand, m1, m2, sc[0], sc[1]);
            }

            if (dq1){
                s1 = 0; s2 = 3 * HANDS_PER_ROUND;
            }
            else if (dq2){
                s1 = 3 * HANDS_PER_ROUND;
                s2 = 0;
            }

            System.out.printf(" Round %d result: %d–%d%n", round, s1, s2);
            if (s1 > s2)      { wins1++; System.out.println(" DragonBot wins round"); }
            else if (s2 > s1) { wins2++; System.out.println(" FictPlayBot wins round"); }
            else              { System.out.println(" Round is a tie"); }
        }

        System.out.println("\n=== Match Result ===");
        if (wins1 > wins2)      System.out.printf("DragonBot wins %d–%d%n", wins1, wins2);
        else if (wins2 > wins1) System.out.printf("FictPlayBot wins %d–%d%n", wins2, wins1);
        else                    System.out.printf("Match is a tie %d–%d%n", wins1, wins2);
    }

    private static boolean valid(String m) {
        return m.equals("rock")
                || m.equals("paper")
                || m.equals("scissors")
                || m.equals("fold");
    }

    private static int[] scoreHand(String m1, String m2) {
        // both fold
        if (m1.equals("fold") && m2.equals("fold")) {
            return new int[]{3,3};
        }
        // one folds
        if (m1.equals("fold"))    return new int[]{3,4};
        if (m2.equals("fold"))    return new int[]{4,3};
        // normal vs normal
        if (m1.equals(m2))        return new int[]{3,3};
        boolean p1wins =
                (m1.equals("rock")     && m2.equals("scissors")) ||
                        (m1.equals("paper")    && m2.equals("rock"))     ||
                        (m1.equals("scissors") && m2.equals("paper"));
        return p1wins ? new int[]{5,0} : new int[]{0,5};
    }
}