package leetcodedaily;

import java.util.*;

import static java.lang.Math.max;

public class MaximizeSubarraysAfterRemovingOneConflictingPair {
    public long maxSubarraysBasic(int n, int[][] conflictingPairs) {
        // arr[i] = i+1
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        // adj.get(x) maps each smaller conflict-endpoint -> its count, keyed by the larger endpoint x
        List<TreeMap<Integer, Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new TreeMap<>());
        }

        // Build the multiset of conflicts
        for (int[] edge : conflictingPairs) {
            int u = edge[0], v = edge[1];
            if (u > v) {
                adj.get(u).put(v, adj.get(u).getOrDefault(v, 0) + 1);
            } else {
                adj.get(v).put(u, adj.get(v).getOrDefault(u, 0) + 1);
            }
        }

        long res = 0;
        // Try removing each conflicting pair once
        for (int x = 0; x < conflictingPairs.length; x++) {
            int u = conflictingPairs[x][0];
            int v = conflictingPairs[x][1];

            // --- remove exactly one copy of (u,v)
            if (u > v) {
                TreeMap<Integer, Integer> map = adj.get(u);
                int c = map.get(v);
                if (c == 1) map.remove(v);
                else        map.put(v, c - 1);
            } else {
                TreeMap<Integer, Integer> map = adj.get(v);
                int c = map.get(u);
                if (c == 1) map.remove(u);
                else        map.put(u, c - 1);
            }

            // --- two-pointer sweep to count valid subarrays
            long count = 0;
            int i = 0;
            for (int j = 0; j < n; j++) {
                // the largest “forbidden start” for any pair ending at arr[j]
                int block = arr[i] - 1;
                TreeMap<Integer, Integer> map = adj.get(arr[j]);
                if (!map.isEmpty()) {
                    block = map.lastKey();
                }

                // advance i until arr[i] > block
                while (i < n && arr[i] <= block) {
                    i++;
                }
                count += (j - i + 1);
            }

            res = Math.max(res, count);

            // --- add the pair (u,v) back
            if (u > v) {
                adj.get(u).put(v, adj.get(u).getOrDefault(v, 0) + 1);
            } else {
                adj.get(v).put(u, adj.get(v).getOrDefault(u, 0) + 1);
            }
        }

        return res;
    }

    public long maxSubarrays(int n, int[][] conflictingPairs) {
        long valid = 0;

        // conflictingPoints[i] = list of points which conflict with i
        List<List<Integer>> conflictingPoints = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            conflictingPoints.add(new ArrayList<>());
        }

        // Build the conflicting points list
        for (int[] p : conflictingPairs) {
            int a = Math.min(p[0], p[1]);
            int b = Math.max(p[0], p[1]);
            conflictingPoints.get(b).add(a);
        }

        int maxConflict = 0;
        int secondMaxConflict = 0;

        // extra[i] = number of extra subarrays by removing the conflicting point i
        long[] extra = new long[n + 1];

        // Process each end point of subarrays
        for (int end = 1; end <= n; end++) {
            // Check all conflicting points of 'end'
            for (int u : conflictingPoints.get(end)) {
                if (u >= maxConflict) {
                    secondMaxConflict = maxConflict;
                    maxConflict = u;
                } else if (u > secondMaxConflict) {
                    secondMaxConflict = u;
                }
            }
            // Count valid subarrays ending at 'end'
            valid += end - maxConflict;
            // Add extra subarrays count
            extra[maxConflict] += maxConflict - secondMaxConflict;
        }

        // Find the maximum value in extra array
        long maxExtra = 0;
        for (long val : extra) {
            if (val > maxExtra) {
                maxExtra = val;
            }
        }

        return valid + maxExtra;
    }
}
