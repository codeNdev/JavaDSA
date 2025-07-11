package datastructures;

import java.util.*;

public class ArrayListDemo {
    static void printList(List<Integer> list){
        for (Integer it:list){
            System.out.print(it+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Collection<Integer> collection= new ArrayList<>();
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(10);
        list.add(15);
        for (int i=0;i<list.size();i++){
            if(i==1){
                list.add(25);
            }
            System.out.println(list.get(i));
        }
        list.sort(null);
        System.out.println(list.size());
        for (int i=0;i<list.size();i++) {
            System.out.println(list.get(i));
        }
        printList(list);
        Collections.sort(list);
        System.out.println();
        System.out.println("Adding element at a specific Index: ");
        list.add(2,7);
        printList(list);
        System.out.println("Removing the last element : " +list.removeLast());
        printList(list);
        System.out.println("Size is: "+ list.size());
        System.out.println("The Last Element is: "+ list.getLast());
        System.out.println("Is the List Empty: "+ list.isEmpty());
        List<String> strList= Arrays.asList("Shibamdfdfre","Sanjeeb","Sambit","Rituraj");
        strList.sort((a,b)->{
            if(a.length()<b.length()){
                return -1;
            }else{
                return 1;
            }
        });
        for (String s:strList){
            System.out.println(s);
        }
    }
}
