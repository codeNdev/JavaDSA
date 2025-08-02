package leetcodedaily;

import java.util.*;

public class RearrangingFruits {
    public long minCost(int[] basket1, int[] basket2) {
        // Step 1: Calculate frequency differences and find the global minimum fruit cost.
        NavigableMap<Integer, Integer> diff = new TreeMap<>();
        long minVal = Long.MAX_VALUE;

        // Populate the difference map and find the minimum value in one pass.
        for (int fruit : basket1) {
            diff.put(fruit, diff.getOrDefault(fruit, 0) + 1);
            minVal = Math.min(minVal, fruit);
        }
        for (int fruit : basket2) {
            diff.put(fruit, diff.getOrDefault(fruit, 0) - 1);
            minVal = Math.min(minVal, fruit);
        }

        // Step 2: Create a single list of all fruits that need to be moved.
        List<Integer> swaps = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            int fruit = entry.getKey();
            int difference = entry.getValue();

            // If the difference is odd, the total count is odd, so it's impossible.
            if (difference % 2 != 0) {
                return -1;
            }

            // The number of fruits to move is half the absolute difference.
            int numToMove = Math.abs(difference) / 2;
            for (int i = 0; i < numToMove; i++) {
                swaps.add(fruit);
            }
        }

        // The 'swaps' list is naturally sorted because it's populated from a TreeMap.

        long totalCost = 0;

        // Step 3: Calculate the minimum cost.
        // We need to perform swaps.size() / 2 swaps.
        // The cheapest swaps involve the smallest-cost fruits.
        for (int i = 0; i < swaps.size() / 2; i++) {
            // The cost for one swap is either the fruit's own value (by pairing it with a larger one)
            // or using the global minimum fruit twice as an intermediary.
            long swapCost = swaps.get(i);
            long indirectSwapCost = 2 * minVal;

            totalCost += Math.min(swapCost, indirectSwapCost);
        }

        return totalCost;
    }
}
