package basic;

import java.util.Scanner;

public class SpecialNumbers {
    public static long nthFibonacci(long n){
        if(n==0){
            return 0;
        }
        long prev=0,curr=1;
        System.out.print(prev+ " "+ curr+" ");
        for(long i=2;i<=n;i++){
            long temp=curr+prev;
            prev=curr;
            curr=temp;
            System.out.print(curr+" ");
        }
        return curr;
    }

    public static long  tribonacciSeries(long n) {
        long first=0,second=1,third=1;
        if(n==0){
            return first;
        }else if(n==1){
            return second;
        }
        System.out.print(first+" "+ second+ " "+ third+" ");
        for(long i=3;i<=n;i++){
            long next=first+second+third;
            first=second;
            second=third;
            third=next;
            System.out.print(third+ " ");
        }
        return third;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Number");
        long n=sc.nextInt();
        System.out.println("The Nth Fibonacci Number is "+ nthFibonacci(n));
        tribonacciSeries(n);
        sc.close();
    }
}
