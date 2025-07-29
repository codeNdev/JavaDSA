package leetcodedaily;

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
}
