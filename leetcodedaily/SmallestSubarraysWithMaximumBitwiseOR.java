package leetcodedaily;

import java.util.Arrays;

public class SmallestSubarraysWithMaximumBitwiseOR {
    boolean canIRemove(int[] nums,int[] count, int i){
        int curr=nums[i];
        int idx=0;
        while(curr>0){
            if((curr&1)==1){
                if(count[idx]==1){
                    return false;
                }
            }
            curr>>=1;
            idx++;
        }
        return true;
    }
    void remove(int[] count, int val){
        int idx=0;
        while(val>0){
            if((val&1)==1){
                count[idx]--;
            }
            val>>=1;
            idx++;
        }
    }
    void add(int[] count,int curr){
        int idx=0;
        while(curr>0){
            if((curr&1)==1){
                count[idx]++;
            }
            curr>>=1;
            idx++;
        }
    }
    /**
     * For each index i, find the length of the smallest sub-array starting at i
     * such that the bitwise OR of that sub-array is the maximum possible OR
     * that can be obtained from i to the end of the array.
     *
     * Approach:
     * 1. Maintain an array 'count' of size 32, where count[j] stores how many numbers
     *    in the current sub-array have the j-th bit set.
     * 2. Traverse the array from right to left:
     *      - Add nums[st] to 'count' (update bit frequencies).
     *      - Try to shrink the sub-array from the right (end pointer) as long as removing nums[end]
     *        does not reduce the OR value (checked using canIRemove()).
     *      - The smallest sub-array length for index st will be (end - st + 1).
     *
     * Helper Functions:
     * - add(count, val)    → Updates count[] by incrementing bits set in val.
     * - remove(count, val) → Updates count[] by decrementing bits set in val.
     * - canIRemove(nums, count, i)
     *        → Checks if nums[i] can be removed without losing any bit that
     *          appears only once in the current sub-array.
     *
     * Complexity:
     * - Time Complexity: O(32 × n) = O(n), as there are at most 32 bits.
     * - Space Complexity: O(32) = O(1) extra space.
     *
     * @param nums Input integer array
     * @return ans[i] = length of smallest sub-array starting at i
     *                  that achieves maximum possible OR from i.
     */
    public int[] smallestSubarrays(int[] nums) {
        int n=nums.length;
        int[]count=new int[32];
        int end=n-1;
        int[] ans=new int[n];
        for(int st=n-1;st>=0;st--){
            int curr=nums[st];
            add(count,curr);
            while(end>st && canIRemove(nums,count,end)){
                remove(count,nums[end]);
                end--;
            }
            ans[st]=(end-st+1);
        }
        return ans;
    }

    /**
     * For each index i, find the length of the smallest sub-array starting at i
     * such that the bitwise OR of that sub-array is the maximum possible OR
     * that can be obtained starting from i to the end of the array.
     *
     * Approach:
     * 1. Maintain an array 'pos' of size 32, where pos[j] stores the nearest index
     *    (towards the right) where the j-th bit is set.
     * 2. Traverse the array from right to left:
     *      - For the current index i, update pos[j] for every bit j that is set in nums[i].
     *      - Compute 'mx' as the maximum value among all pos[j] and i itself.
     *        This 'mx' represents the farthest index that must be included
     *        to cover all set bits seen so far.
     * 3. The answer for index i will be the length of the sub-array from i to mx, i.e., (mx - i + 1).
     *
     * @param nums Input integer array
     * @return An integer array where ans[i] is the smallest sub-array length
     *         starting at i that achieves the maximum possible OR.
     */
    public int[] smallestSubarrays2(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        int[]pos=new int[32];
        Arrays.fill(pos,-1);
        for (int i=n-1;i>=0;i--){
            int mx=i;
            int curr=nums[i];
            for(int j=0;j<32;j++){
                if((curr & (1<<j))!=0){
                    pos[j]=i;
                }
                mx=Math.max(pos[j],mx);
            }
            ans[i]=mx-i+1;
        }
        return ans;
    }
}
