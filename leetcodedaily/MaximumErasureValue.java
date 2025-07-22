package leetcodedaily;

import java.util.HashSet;
import java.util.Set;

public class MaximumErasureValue {
//    public int maximumUniqueSubarray(int[] nums) {
//        Set<Integer> set=new HashSet<>();
//        int n=nums.length;
//        int i=0;
//        long sum=0;
//        long maxScore=0;
//        for(int j=0;j<n;j++){
//            sum+=nums[j];
//            while (set.contains(nums[j])){
//                set.remove(nums[i]);
//                sum-=nums[i];i++;
//            }
//            set.add(nums[j]);
//            maxScore=Math.max(maxScore,sum);
//        }
//        return (int) maxScore;
//    }
    public int maximumUniqueSubarray(int[] nums) {
        int[] freq=new int[10001];
        int n=nums.length;
        int i=0;
        long sum=0;
        long maxScore=0;
        for(int j=0;j<n;j++){
            sum+=nums[j];
            while (freq[nums[j]]>0){
                freq[nums[i]]--;
                sum-=nums[i];i++;
            }
            freq[nums[j]]++;
            maxScore=Math.max(maxScore,sum);
        }
        return (int) maxScore;
    }
}
