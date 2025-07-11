package ds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        list.add(1);list.add(2);list.add(3);
        Iterator<Integer> itr=list.iterator();

        while (itr.hasNext()){
            int val=itr.next();
            System.out.println(val);
            if(val%2==0){
                itr.remove();
            }
            System.out.println(list);
        }
    }
}
