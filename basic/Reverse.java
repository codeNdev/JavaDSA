package basic;

import java.util.Arrays;

// in java we can't send primitive values by reference, whereas objects are always passed by refence
public class Reverse {

    public static String reverse(String s){
//        String ans=new StringBuilder(s).reverse().toString();
        char arr[]=s.toCharArray();
        int i=0,j=arr.length-1;
        while (i<j){
//            swap operation manual perform
            char temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
//        used for debugging ==> it is just array representation in the form of a String
//        [1, 2, 3, 3, 2, 1]
//        return Arrays.toString(arr); //Incorrect
//        [C@50040f0c
//        this method shows the object class and the hashcode of its memory-reference
//        return arr.toString(); // Incorrect

        return new String(arr);
    }
}
