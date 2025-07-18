package leetcodedaily;

import java.util.Arrays;

import static java.lang.Math.max;

public class MaximumLengthOfValidSubsequence {
    long diffCount(int []nums,int prev){
        long count=0;
        for (int i:nums){
            if(((prev^i)&1) ==1){
                count++;
                prev=i;
            }
        }
        return count;
    }
    public int maximumLength(int[] nums) {
        int n=nums.length;
        long oddCount= Arrays.stream(nums).filter(a -> a % 2 != 0).count();
        long evenCount=Arrays.stream(nums).filter(a->a%2==0).count();
        long oddStart=diffCount(nums,1);
        long evenStart=diffCount(nums,0);
        return (int) max(max(oddStart,evenStart), max(oddCount,evenCount));
    }
    public int maximumLength(int[] nums, int k) {
        int n=nums.length;
        int [][] dp=new int[k][n];
        for (int i=0;i<k;i++){
            Arrays.fill(dp[i],1);
        }
        int res = 0;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                int mod=(nums[i]+nums[j])%k;
                dp[mod][i]=max(dp[mod][i],dp[mod][j]+1);
                res=max(dp[mod][i],res);
            }
        }
        return res;
    }
}
