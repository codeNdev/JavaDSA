package leetcodepractice;

public class RemoveColoredPiecesIfBothNeighborsAreSameColor {
    /**
     * Question: 2038
     * Intuition:
     * 1. Two different segments can never get merged, that means we only need to solve problem for individual segment
     * Approach:
     * 1. Alice can only remove 'A' pieces that have both neighbors as 'A'.
     *    This means a segment of consecutive 'A's of length L gives Alice (L-2) possible moves.
     *    Similarly, a segment of consecutive 'B's of length L gives Bob (L-2) possible moves.
     *
     * 2. So, the problem reduces to:
     *    - Count the total possible moves for Alice (a) from all 'A' segments.
     *    - Count the total possible moves for Bob (b) from all 'B' segments.
     *
     * 3. Rules:
     *    - If a > b → Alice has more moves → She wins because she moves first.
     *    - Else → Bob wins (either equal or Bob has more moves).
     *
     * 4. Algorithm:
     *    - Iterate through the string and group consecutive identical characters.
     *    - For each group of 'A's or 'B's:
     *        moves += max(0, count - 2)  (only if count >= 3)
     *    - At the end, compare a and b.
     *
     * 5. Complexity:
     *    - Time Complexity: O(n) (single pass through the string)
     *    - Space Complexity: O(1) (only counters used)
     *
     * Example Walkthrough:
     * Input: "AAABABB"
     * Groups: "AAA", "B", "A", "BB"
     * Alice's moves = (3-2)=1, Bob's moves = (2-2)=0
     * a=1, b=0 → Alice wins → true
     */
    public boolean winnerOfGame(String colors) {
        int n = colors.length();
        char prev = 'z';
        int count = 0;
        int a = 0, b = 0;

        // Traverse the string to count consecutive segments
        for (char ch : colors.toCharArray()) {
            if (ch == prev) {
                count++; // same char, increase segment size
            } else {
                // Process the previous segment
                if (prev == 'A') {
                    a += Math.max(0, count - 2);
                } else if (prev == 'B') {
                    b += Math.max(0, count - 2);
                }
                prev = ch;
                count = 1; // reset count for new segment
            }
        }

        // Process the last segment
        if (prev == 'A') {
            a += Math.max(0, count - 2);
        } else if (prev == 'B') {
            b += Math.max(0, count - 2);
        }

        // Alice wins only if she has more possible moves
        return a > b;
    }
}
