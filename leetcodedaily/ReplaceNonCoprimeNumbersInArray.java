package leetcodedaily;

import java.util.ArrayList;
import java.util.List;

public class ReplaceNonCoprimeNumbersInArray {
    long gcd(long x, long y) {
        while (true){
            long mx=Math.max(x,y);
            long mn=Math.min(x,y);
            if (mn==0){
                return mx;
            }
            x=mn;
            y=mx%mn;
        }
    }
    boolean isCoprime(List<Integer> ans,long b){
        long a=ans.getLast();
        return gcd(a, b)==1;
    }
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> ans=new ArrayList<>();
        int n=nums.length;
        for(long i:nums){
            long val=i;
            while(ans.size()>=1 && !isCoprime(ans,val)){
                long removed=ans.removeLast();
                val=(removed/gcd(removed,val))*val;
            }
            ans.add((int) val);
        }
        return ans;
    }
}
