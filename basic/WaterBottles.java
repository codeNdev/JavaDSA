package basic;

public class WaterBottles {
//    Leetcode: 1518
    public static int numWaterBottles(int numBottles,int numExchanges){
//
        int drunkBottles=0;
        int emptyBottles=0;
        while(numBottles>0 || emptyBottles>=numExchanges){
            drunkBottles+=numBottles;// drink all filled bottles
            emptyBottles+=numBottles;numBottles=0;
            int converted=emptyBottles/numExchanges;
            numBottles=converted;
            emptyBottles=emptyBottles%numExchanges;
        }
        return drunkBottles;
    }

    public static void main(String[] args) {
        System.out.println(numWaterBottles(100,9));
    }
}
