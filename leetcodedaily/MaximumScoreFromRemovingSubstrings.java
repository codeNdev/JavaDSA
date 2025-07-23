package leetcodedaily;

import java.util.Stack;

public class MaximumScoreFromRemovingSubstrings {
    private String solve(String s,String pattern,int x,int [] ans){
        StringBuilder sb=new StringBuilder();
        for (char ch:s.toCharArray()){
            if(!sb.isEmpty() && sb.charAt(sb.length() - 1)==pattern.charAt(0) && ch==pattern.charAt(1) ){
                ans[0]+=x;
                sb.deleteCharAt(sb.length() - 1);
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    public int maximumGain(String s, int x, int y) {
        int [] ans=new int[1];
        if(x>y){
            String str=solve(s,"ab",x,ans);
            solve(str,"ba",y,ans);
        }else{
            String str=solve(s,"ba",y,ans);
            solve(str,"ab",x,ans);
        }
        return ans[0];
    }
}
