package game;
import java.util.HashMap;
import java.util.Map;

public class DragonBot implements Strategy {
    private static final String[] HANDS = {
            "rock",
            "paper",
            "scissors"
    };

    @Override
    public String throwHand(String[] player, String[] opponent, int handNumber) throws Exception {
        try {
            // For the very first move, default to "rock"
            if (handNumber <= 1) {
                return "rock";
            }

            // Build global frequency of opponent’s rock,paper,scissors
            Map < String, Integer > globalFreq = new HashMap < > ();
            for (String h: HANDS) {
                globalFreq.put(h, 0);
            }
            int globalSum = 0;
            for (String m: opponent) {
                if (globalFreq.containsKey(m)) {
                    globalFreq.put(m, globalFreq.get(m) + 1);
                    globalSum++;
                }
            }

            String predicted = null;
            Map < String, Integer > localFreq = new HashMap < > ();
            int localSum = 0;
            if (handNumber > 2) {
                String last1 = opponent[handNumber - 2];
                String last2 = opponent[handNumber - 3];

                for (String h: HANDS) {
                    localFreq.put(h, 0);
                }

                // Scan history for transitions [last2, last1] -> next
                for (int i = 2; i < opponent.length; i++) {
                    if (opponent[i - 2].equals(last2) && opponent[i - 1].equals(last1)) {
                        String nxt = opponent[i];
                        localFreq.put(nxt, localFreq.getOrDefault(nxt, 0) + 1);
                        localSum++;
                    }
                }

                if (localSum > 0) {
                    predicted = argMax(localFreq);
                }
            }

            if (predicted == null) {
                if (globalSum > 0) {
                    predicted = argMax(globalFreq);
                } else {
                    //Pick a random normal hand
                    predicted = HANDS[(int)(Math.random() * 3)];
                }
            }

            String myMove = counterTo(predicted);

            Map < String, Integer > dist = (localSum > 0) ? localFreq : globalFreq;
            int distSum = (localSum > 0) ? localSum : globalSum;
            double probLose = 0.0;
            if (distSum > 0) {
                String beatsMe = counterTo(myMove);
                probLose = (double) dist.getOrDefault(beatsMe, 0) / distSum;
            }

            // If the loss‐chance exceeds 50%, fold for a safe 3 points
            if (probLose > 0.5) {
                return "fold";
            } else {
                return myMove;
            }
        } catch (Exception e) {
            throw new Exception(this.getClass().getSimpleName());
        }
    }

    // Pick the map key with the highest integer value
    private String argMax(Map < String, Integer > map) {
        String best = null;
        int max = Integer.MIN_VALUE;
        for (Map.Entry < String, Integer > e: map.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                best = e.getKey();
            }
        }
        return best;
    }

    // Return the hand that beats the given hand
    private String counterTo(String hand) {
        switch (hand) {
            case "rock":
                return "paper";
            case "paper":
                return "scissors";
            case "scissors":
                return "rock";
            default:
                return "rock";
        }
    }
}