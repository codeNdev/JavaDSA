package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Palindrome {
    public static boolean isPalindrome(int n){
//        Using String

//        String num=Integer.toString(n);
//        System.out.println(num);
//        String rev=Reverse.reverse(num);
//        System.out.println(rev);
//        if(rev.equals(num)){
//            return true;
//        }else{
//            return false;
//        }

//        without String
//        Negative number is always non-palindromic
//        -121 and 121- not equal
        System.out.println(n);
        int og=n;
        if(n<0){
            return false;
        }
        int rev=0;
        while(n>0){
            rev*=10;// 9-> 90
            rev+=(n%10); // 90-> 97
            n/=10;// 17->1
        }
        System.out.println(rev);
        if(rev==og){
            return true;
        }else{
            return false;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number");
        int n=sc.nextInt();
        if(isPalindrome(n)){
            System.out.println("The number is a palindrome");
        }else{
            System.out.println("The number is not a palindrome");
        }
//        setting a list with same values
        List<Integer> list = new ArrayList<>(Collections.nCopies(n, 0));
        System.out.println(list.size());
        System.out.println(list);
        list.add(1);
        System.out.println(list);
        list.set(7,10);
        System.out.println(list);
        sc.close();  // Close the scanner to avoid resource leak
    }
}
