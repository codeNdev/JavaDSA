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
        long count = 0;
        int []diff=new int[n];
        int i = 0;
        for (int j = 0; j < n; j++) {
            // the largest “forbidden start” for any pair ending at arr[j]
            int block = arr[i] - 1;
            TreeMap<Integer, Integer> map = adj.get(arr[j]);
            if (!map.isEmpty()) {
                block = map.lastKey();
                Integer second = map.lowerKey(block);
                int val=map.get(block);
                if(val==1){
                    int mx=arr[i];
                    if(second!=null){
                        mx=Math.max(mx,second);
                    }
                    diff[j]=block-mx;
                }
            }

            // advance i until arr[i] > block
            while (i < n && arr[i] <= block) {
                i++;
            }
            count += (j - i + 1);
        }
        long res=0;

        return res;
    }
}
