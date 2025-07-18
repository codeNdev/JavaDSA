package leetcodepractice;

import java.util.Arrays;

public class MaximumNumberOfEventsThatCanBeAttended {
    public static int maxEvents(int[][] events) {
        Arrays.sort(events,(a,b)->{
            if(a[0]!=b[0]){
                return Integer.compare(b[0],a[0]);
            }
            return Integer.compare(a[1],b[1]);
        });
        int ans=0;
        int curr=0;
        for(int[] event:events){
            int start=event[0];
            int end=event[1];
            System.out.print(start+" " + end+" "+ curr+" "+ans);
            curr=Math.max(start,curr);
            if(curr<=end){
                ans++;
                System.out.print(", "+ ans);
                curr++;
            }
            System.out.println();

        }
        return ans;
    }

    public static void main(String[] args) {
        int [][]events= new int[][]{{1, 4}, {4, 4}, {2, 2}, {3, 4}, {1, 1}};
        System.out.println(maxEvents(events));
    }
}
