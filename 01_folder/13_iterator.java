import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

class IteratorExample{

    public static void main(String[] args){

        ArrayList<Object> al = new ArrayList<Object>();
        al.add(20);
        al.add("b");

        Iterator<Object> itr = al.iterator();
        while(itr.hasNext()){
            Object obj = itr.next();
            System.out.println(obj);
        }

        LinkedHashSet<Object> lhs = new LinkedHashSet<Object>();
        lhs.add(30);
        lhs.add("c");
        Iterator<Object> itr_2 = lhs.iterator();
        while(itr_2.hasNext()){
            Object obj = itr_2.next();
            System.out.println(obj);
        }

    }

}