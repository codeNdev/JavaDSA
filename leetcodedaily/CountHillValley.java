package leetcodedaily;

import java.util.ArrayList;

public class CountHillValley {
    public int countHillValley(int[] nums) {
        ArrayList<Integer> arr=new ArrayList<>();
        int prev=Integer.MAX_VALUE;
        for(int val:nums){
            if(val!=prev){
                arr.add(val);
            }
            prev=val;
        }
        int ans=0;
        for(int i=1;i<arr.size()-1;i++){
            if(arr.get(i-1)>arr.get(i) && arr.get(i+1)>arr.get(i)){
                ans++;
            }else if(arr.get(i-1)<arr.get(i) && arr.get(i+1)<arr.get(i)){
                ans++;
            }
        }
        return ans;
    }
}
