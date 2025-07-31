package leetcodepractice;

public class LongestNiceSubarray {
    /**
     *
     * @param nums
     * @return
     */
    public int longestNiceSubarray(int[] nums) {
        int curr=0;
        int n=nums.length;
        int maxLen=0;
        int st=0;
        for(int i=0;i<n;i++){
            while((curr+nums[i])>(curr|nums[i])){
                curr^=nums[st];
                st++;
            }
            curr|=nums[i];
            maxLen=Math.max(maxLen,i-st+1);
        }
        return maxLen;
    }
}
