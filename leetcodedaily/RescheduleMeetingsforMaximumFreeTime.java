package leetcodedaily;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

//https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-ii/?envType=daily-question&envId=2025-07-10
class Pair{
    int start;
    int end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + start +
                ", " + end +
                ')';
    }
}
public class RescheduleMeetingsforMaximumFreeTime {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n=startTime.length;
        Pair [] events=new Pair[n+2];
        for(int i=0;i<n;i++){
            events[i]=new Pair(startTime[i],endTime[i]);
        }
        events[n]=new Pair(0,0);
        events[n+1]=new Pair(eventTime,eventTime);
        Arrays.sort(events,(a, b) -> Integer.compare(a.start, b.start));
        int []freeTimes=new int[n+1];
        int []leftMax=new int[n+1];
        int mx=0;
        for(Pair it:events){
            System.out.println(it);
        }

        for(int i=1;i<=n+1;i++){
            freeTimes[i-1]=events[i].start-events[i-1].end;
            mx=Math.max(mx,freeTimes[i-1]);
            leftMax[i-1]=mx;
        }
        int ans=mx;
        int rightMax=0;
        for(int i=n;i>0;i--){
            int freeSpace=freeTimes[i]+freeTimes[i-1];
            int currentEvent=events[i].end-events[i].start;
            ans=Math.max(freeSpace,ans);
            if((i>1 && leftMax[i-2]>=currentEvent) || rightMax>=currentEvent){
                ans=Math.max(freeSpace+currentEvent,ans);
            }
            if(i+1<n+1){
                rightMax=Math.max(rightMax,freeTimes[i+1]);
            }
        }
        return ans;
    }
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n=startTime.length;
        long[] prefixEventTime=new long[n];
        long time=0;
        for(int i=0;i<n;i++){
            time+=(endTime[i]-startTime[i]);
            prefixEventTime[i]=time;
        }
        long ans=0;
        for(int i=k-1;i<n;i++){
            int leftIdx=i-k;
            int rightIdx=i+1;
            long leftVal=0;
            long rightVal=eventTime;
            long timeTobeRemoved=0;
            if(leftIdx>=0){
                leftVal=endTime[leftIdx];
                timeTobeRemoved=prefixEventTime[leftIdx];
            }
            if(rightIdx<n){
                rightVal=startTime[rightIdx];
            }
            long totalTime=rightVal-leftVal;
            long occupiedTime=prefixEventTime[i]-timeTobeRemoved;
            long freeTime=totalTime-occupiedTime;
            ans=Math.max(freeTime,ans);
        }
        return (int) ans;
    }
}
