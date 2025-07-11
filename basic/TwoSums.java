package basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSums {
//    Leetcode -- 01
    public int[] twoSum(int[] nums, int target) {
        int n=nums.length;
//        Arrays.sort(nums);  // sorts ds.array correctly
        Map<Integer,Integer> valtoIdx=new HashMap<>();
        for(int i=0;i<n;i++){
            int reqd=target-nums[i];
            if(valtoIdx.containsKey(reqd)){
                return new int[]{valtoIdx.get(reqd),i};
            }
            valtoIdx.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        TwoSums twoSums=new TwoSums();
        int []arr=twoSums.twoSum(new int[]{2,3,1,4,7,5,6},9);
        System.out.println(Arrays.toString(arr));
    }
}
