import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

class EnumarateExample{

    public static void main(String[] args){

        Vector<Object> v = new Vector<Object>();
        v.add(10);
        v.add("a");
        Enumeration<Object> e = v.elements();
        while(e.hasMoreElements()){
            Object obj = e.nextElement();
            System.out.println(obj);
        }

        ArrayList<Object> al = new ArrayList<Object>();
        al.add(20);
        al.add("b");
        Enumeration<Object> e_2 = Collections.enumeration(al);
        while(e_2.hasMoreElements()){
            Object obj = e_2.nextElement();
            System.out.println(obj);
        }

    }

}