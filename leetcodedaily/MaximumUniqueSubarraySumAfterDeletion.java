package leetcodedaily;

import java.util.HashSet;

public class MaximumUniqueSubarraySumAfterDeletion {
    public int maxSum(int[] nums) {
        int mx=Integer.MIN_VALUE;
        HashSet<Integer> set=new HashSet<>();
        int sum=0;
        for(int i:nums){
            mx=Math.max(mx,i);
            if(i>0 && !set.contains(i)){
                sum+=i;
                set.add(i);
            }
        }
        if(mx<=0){
            return mx;
        }
        return sum;
    }
}
