package leetcodedaily;

public class LongestSubarrayWithMaximumBitwiseAND {

    /**
     * Approach:
     * 1. To get the maximum possible bitwise AND of any subarray, we observe that:
     *    - Bitwise AND of multiple numbers is always ≤ the smallest number in that subarray.
     *    - Hence, the maximum possible AND must be equal to the maximum element in the array.
     *      Because including any smaller number would reduce the AND value.
     *
     * 2. Steps:
     *    - Find the maximum value in the array (mx).
     *    - Traverse the array to find the longest contiguous segment (subarray) consisting of mx only.
     *      Because only such a subarray will have AND = mx.
     *
     * 3. Algorithm:
     *    - Iterate through nums to find mx (maximum element).
     *    - Iterate again, and count consecutive occurrences of mx.
     *    - Track the longest streak of mx and return it.
     *
     * 4. Complexity:
     *    - Time Complexity: O(n) (two passes through the array).
     *    - Space Complexity: O(1) (no extra space used).
     *
     * Example:
     * Input: nums = [1, 2, 3, 3, 2, 2]
     * mx = 3
     * Consecutive 3's → [3, 3] → length = 2
     * Output = 2
     */
    public int longestSubarray(int[] nums) {
        // Step 1: Find the maximum value in the array
        int mx = 0;
        for (int i : nums) {
            mx = Math.max(mx, i);
        }

        // Step 2: Find the longest contiguous subarray of mx
        int count = 0;     // count current streak
        int curr = -1;     // track current element
        int mxCount = 0;   // track longest streak of mx

        for (int i : nums) {
            if (i == curr) {
                count++;   // continue the streak
            } else {
                count = 1; // reset streak for new element
                curr = i;
            }

            // If current element is the maximum, update longest streak
            if (curr == mx) {
                mxCount = Math.max(count, mxCount);
            }
        }

        return mxCount; // return longest contiguous subarray length of mx
    }
}

