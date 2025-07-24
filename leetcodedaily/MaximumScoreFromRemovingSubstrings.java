package leetcodedaily;

import java.util.Stack;

public class MaximumScoreFromRemovingSubstrings {
//    T.C. => O(2*n)
//    S.C. => O(n) --> To store the string
//    private String solve(String s,String pattern,int x,int [] ans){
//        StringBuilder sb=new StringBuilder();
//        for (char ch:s.toCharArray()){
//            if(!sb.isEmpty() && sb.charAt(sb.length() - 1)==pattern.charAt(0) && ch==pattern.charAt(1) ){
//                ans[0]+=x;
//                sb.deleteCharAt(sb.length() - 1);
//            }else{
//                sb.append(ch);
//            }
//        }
//        return sb.toString();
//    }
//    public int maximumGain(String s, int x, int y) {
//        int [] ans=new int[1];
//        if(x>y){
//            String str=solve(s,"ab",x,ans);
//            solve(str,"ba",y,ans);
//        }else{
//            String str=solve(s,"ba",y,ans);
//            solve(str,"ab",x,ans);
//        }
//        return ans[0];
//    }

//    Most optimized approach
//    T.C. => O(2*n) (traversal+ reversal)
//    S.C. => O(n) (Java-> To store reverse string),
//    S.C. => O(1) (C++ -> Reverses the string in-place)

    public int maximumGain(String s, int x, int y) {
        if(y>x){
            int temp=x;
            x=y;y=temp;
            // reverse the array to maintain the logic
            s=new StringBuilder(s).reverse().toString();
        }
        int a=0,b=0,total=0;
        for(char ch:s.toCharArray()){
            if(ch=='a'){
                a++;
            }else if(ch=='b'){
                if(a>0){
                    total+=x;
                    a--;
                }else{
                    b++;
                }
            }else{
                total+=(y*Math.min(a,b));
                a=0;
                b=0;
            }
        }
        total+=(y*Math.min(a,b));
        return total;
    }
}
