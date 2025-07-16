package leetcodedaily;

import java.util.Arrays;

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
        return (int) Math.max(Math.max(oddStart,evenStart),Math.max(oddCount,evenCount));
    }
}
