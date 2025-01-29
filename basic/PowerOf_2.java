package basic;

public class PowerOf_2 {
    static public boolean isPowerOf_2(int n){
        if(n<=0){
            return false;
        }
//        Bitwise-operation
//        return ((n&(n-1))==0);

//        using math
        while((n&1)==0){
//            while the number is even
            n/=2;
        }
        if(n==1){
            return true;
        }
        return false;
    }
    public static boolean isPowerOf(int n,int p){
        if(p==1){
            return n==1;
        }
        while (n%p==0){
            n/=p;
        }
        if(n==1){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        if(isPowerOf_2(512)){
            System.out.println("Power of 2");
        }else{
            System.out.println("Not a power of 2");
        }
        if (isPowerOf(100,11)){
            System.out.println(100 +" is a power of "+ 11);
        }else{
            System.out.println(100 +" is not a power of "+ 11);
        }
    }
}
