import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/my-calendar-iii/
// Question : Implement a MyCalendarThree class to store your events. A new event can always be added.
// Your class will have one method, book(int start, int end). Formally, this represents a booking on the half
// open interval [start, end), the range of real numbers x such that start <= x < end. A K-booking happens when K
// events have some non-empty intersection (ie., there is some time that is common to all K events.)
// For each call to the method MyCalendar.book, return an integer K representing the largest integer such that
// there exists a K-booking in the calendar. Your class will be called like this: MyCalendarThree
// cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
// https://www.tutorialspoint.com/Difference-between-TreeMap-HashMap-and-LinkedHashMap-in-Java
// We will use treeMap which maintains the order. Its insert and serch complexity in O(log n)


class MyCalendar {
    int minTime = Integer.MAX_VALUE;
    int maxTime = -Integer.MAX_VALUE;
    Map<Integer, Integer> myMap = new TreeMap<Integer, Integer>();

    public MyCalendar(){

    }

    public int book(int inpStartTime, int inpEndTime){
        this.minTime = Math.min(this.minTime, inpStartTime);
        this.maxTime = Math.max(this.maxTime, inpEndTime);

        Integer count = myMap.get(inpStartTime);
        if (count == null){
            myMap.put(inpStartTime, 1);
        } else{
            myMap.put(inpStartTime, count + 1);
        }

        count = myMap.get(inpEndTime);
        if (count == null){
            myMap.put(inpEndTime, -1);
        } else{
            myMap.put(inpEndTime, count - 1);
        }

        int result = 0;
        int runningCount = 0;

        for (Integer key : this.myMap.keySet()){
            Integer value = this.myMap.get(key);
            runningCount += value;
            result = Math.max(result, runningCount);
        }

        return result;

    }

    public void printValue(){
        System.out.println(this.minTime);
        System.out.println(this.maxTime);
        System.out.println(this.myMap);
    }
    
    public static void main(String args[]){
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(50, 60));
        System.out.println(myCalendar.book(10, 40));

        System.out.println(myCalendar.book(5, 15));
        System.out.println(myCalendar.book(5, 10));
        System.out.println(myCalendar.book(25, 55));
    }
}
