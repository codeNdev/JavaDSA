package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AddToArrayForm {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans= new ArrayList<>();
        int idx=num.length-1;
        int carry=0;
        while(k!=0 || idx>=0){
            int lastDigit=k%10;
            int curr=0;
            if(idx>=0){
                curr=num[idx];
            }
            int total=curr+lastDigit+carry;
            ans.add(total%10); // add right-most digit to answer
            carry=total/10; // update the carry for the next iteration
            k/=10;idx--; // moving both the digits one step left
        }
        if(carry!=0){
            while(carry!=0){
                ans.add(carry%10);carry/=10;
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}
