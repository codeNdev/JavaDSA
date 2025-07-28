package leetcodedaily;

public class CountNumberOfMaximumBitwiseOrSubsets {
    int solve(int idx, int[] nums,int maxOr,int curr){
        int n=nums.length;
        if(idx>=n){
            return (curr==maxOr)?1:0;
        }
        int take=solve(idx+1,nums,maxOr,curr|nums[idx]);
        int n_take=solve(idx+1,nums,maxOr,curr);
        return take+n_take;
    }
    public int countMaxOrSubsets(int[] nums) {
        int maxOr=0;
        for(int i:nums){
            maxOr|=i;
        }
        return solve(0,nums,maxOr,0);
    }
}
