package leetcodedaily;

import java.util.HashSet;
import java.util.Set;

public class BitwiseORsOfSubarrays {
    /**
     * Returns the number of distinct bitwise OR values of all non-empty contiguous subarrays of the given array.
     *
     * Intuition:
     * Instead of generating all subarrays explicitly (which would take O(n^2)), we only keep track of the
     * bitwise OR results of subarrays ending at the current element. For each element x:
     *  - We compute a new set "curr" by OR-ing x with every value in "prev" (previous step results).
     *  - We also add x itself as a new subarray starting at this position.
     *  - The key property of OR is that once a bit becomes 1, it never goes back to 0, so the number of
     *    distinct OR values at any step is limited by the number of bits (maximum 32 for 32-bit integers).
     *
     * Example:
     * arr = [1, 2, 4]
     *
     * Step 1 (x=1): curr = {1}
     * ans = {1}
     *
     * Step 2 (x=2): curr = {2, 3}  // (0|2=2, 1|2=3)
     * ans = {1, 2, 3}
     *
     * Step 3 (x=4): curr = {4, 6, 7}  // (2|4=6, 3|4=7, 4 alone)
     * ans = {1, 2, 3, 4, 6, 7}
     *
     * Final Answer = 6 (size of ans)
     *
     * Time Complexity:
     * Let n = length of arr and B = number of bits (≤ 32 for int).
     * At each step, "prev" can have at most B elements because OR can only add new set bits,
     * and the maximum number of different bit patterns possible is limited to B.
     *
     * For each element, we compute OR with at most B values → O(B) work per element.
     * Total time complexity = O(n × B) = O(n × 32) = O(n), which is efficient for n up to 50,000.
     *
     * Space Complexity:
     * ans can store at most n × B values in the worst case → O(n × 32).
     * prev and curr each hold at most B values → O(32).
     *
     * @param arr the input integer array
     * @return the count of distinct bitwise OR values of all non-empty subarrays
     */
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans=new HashSet<>();
        Set<Integer> prev=new HashSet<>();
        prev.add(0);
        for(int x:arr){
            Set<Integer> curr=new HashSet<>();
            for(int i:prev){
                curr.add(i | x);
            }
            curr.add(x);
            prev=curr;
            ans.addAll(curr);
        }
        return ans.size();
    }
}
