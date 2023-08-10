import java.util.ArrayList;
import java.util.ListIterator;

class ListIteratorExample{

    public static void main(String[] args){

        ArrayList<Object> al = new ArrayList<Object>();
        al.add("a");
        al.add("b");
        al.add("c");
        al.add(5);
        al.add(6);
        al.add(7);

        System.out.println("Before : " + al);

        int count = 1;
        ListIterator<Object> itr = al.listIterator();
        while(itr.hasNext()){
            Object obj = itr.next();

            if (obj instanceof String){
                String s = (String)obj;
                itr.set(s.toUpperCase());
            } else if (obj instanceof Integer){
                if (count == 1){
                    itr.add(20);
                    count ++;
                }
            }
        }
        System.out.println("After : " + al);

        while(itr.hasPrevious()){
            Object obj = itr.previous();
            System.out.println(obj);
        }
    }

}