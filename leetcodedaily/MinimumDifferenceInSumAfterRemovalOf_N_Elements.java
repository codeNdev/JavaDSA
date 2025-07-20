package leetcodedaily;

import java.util.PriorityQueue;

public class MinimumDifferenceInSumAfterRemovalOf_N_Elements {
    public long minimumDifference(int[] nums) {
        int len=nums.length;
        int n=len/3;
        long[] suffix=new long[n+1];
        long[] prefix=new long[n+1];
        long mx=0,mn=0;
        PriorityQueue<Integer> minHeap=new PriorityQueue<>(); // minHeap --> Minimum at top
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>((a,b)->Integer.compare(b,a)); // maxHeap --> Maximum at top
        for(int i=0;i<n;i++){
            minHeap.add(nums[2*n+i]);
            mx+=nums[2*n+i];
            maxHeap.add(nums[i]);
            mn+=nums[i];
        }
        suffix[n]=mx;
        prefix[0]=mn;
        for (int i=2*n-1;i>=n;i--){
            minHeap.add(nums[i]);
            mx+=nums[i];
            mx-=minHeap.poll();
            suffix[i-n]=mx;
        }
        for (int i=n;i<=2*n-1;i++){
            maxHeap.add(nums[i]);
            mn+=nums[i];
            mn-=maxHeap.poll();
            prefix[i-n+1]=mn;
        }
        long ans=Long.MAX_VALUE;
        for(int i=0;i<=n;i++){
            ans=Math.min(ans,prefix[i]-suffix[i]);
        }
        return ans;
    }
}
