# Java

--------------------------------------------

    class ExceptionHandling {
        public static void main(String[] args) {
            try{
                System.out.println(10/0);
            }
            catch(ArithmeticException ae){
                System.out.println(ae.getMessage());
                System.out.println(ae.toString());
                ae.printStackTrace();
                throw ae;
            }
        }
    }

--------------------------------------------

    enum Months {
        JAN(1), FEB(2), MAR(3);

        private int num;

        Months(int num){
            this.num = num;
        }

        public int getMonths(){
            return this.num;
        }

        public void setMonths(int num){
            this.num = num;
        }
    }

--------------------------------------------

    abstract class Computer {
        
        public abstract String getRAM();
        public abstract String getHDD();
        public abstract String getCPU();
        
        @Override
        public String toString(){
            return "RAM= "+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
        }
    }

--------------------------------------------

    interface ComputerAbstractFactory {

        public Computer createComputer();

    }

--------------------------------------------

    @Override
	public Object clone() throws CloneNotSupportedException{
			List<String> temp = new ArrayList<String>();
			for(String s : this.getEmpList()){
				temp.add(s);
			}
			return new Employees(temp);
	}

    Employees empsNew1 = (Employees) emps.clone();
--------------------------------------------

    ArrayList<Object> al = new ArrayList<Object>();
    al.add(20);
    al.add("b");

    Iterator<Object> itr = al.iterator();
    while(itr.hasNext()){
        Object obj = itr.next();
        System.out.println(obj);
    }

--------------------------------------------

    ItemElement[] items = new ItemElement[]{new Book(20, "1234"),new Book(100, "5678"),
				new Fruit(10, 2, "Banana"), new Fruit(5, 5, "Apple")};
            
--------------------------------------------

    class ThreadSafeSingleton {

        private static ThreadSafeSingleton instance;
        
        private ThreadSafeSingleton(){}
        
        public static synchronized ThreadSafeSingleton getInstance(){
            if(instance == null){
                instance = new ThreadSafeSingleton();
            }
            return instance;
        }
        
    }
--------------------------------------------

    class ShapeFactory {

        private static final HashMap<ShapeType,Shape> shapes = new HashMap<ShapeType,Shape>();

        public static Shape getShape(ShapeType type) {
            Shape shapeImpl = shapes.get(type);

            if (shapeImpl == null) {
                if (type.equals(ShapeType.OVAL_FILL)) {
                    shapeImpl = new Oval(true);
                } else if (type.equals(ShapeType.OVAL_NOFILL)) {
                    shapeImpl = new Oval(false);
                } else if (type.equals(ShapeType.LINE)) {
                    shapeImpl = new Line();
                }
                shapes.put(type, shapeImpl);
            }
            return shapeImpl;
        }
    }

--------------------------------------------
## Watch from here

--------------------------------------------
**String Matching**

    // These two have the same value
    new String("test").equals("test") // --> true 

    // ... but they are not the same object
    new String("test") == "test" // --> false 

    // ... neither are these
    new String("test") == new String("test") // --> false 
--------------------------------------------
 **Custom Comparator**

    import java.util.Collections;
    import java.util.Arrays;
    import java.util.Comparator; // For List of objects
    public static Comparator<Box> getCompByArea(){
        Comparator<Box> comp = new Comparator<Box>(){
            // Defining Anonymous class
            @Override
            public int compare(Box s1, Box s2)
            {
                return s2.getArea() - s1.getArea();
            }        
        };
        return comp;
    }
    Collections.sort(this.boxes, Box.getCompByArea());

    import java.util.Arrays;
    Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]); // For array of objects
--------------------------------------------
**Date Formatter(old)**

https://docs.oracle.com/javase/8/docs/api/java/util/Date.html#compareTo-java.util.Date-

    import java.text.SimpleDateFormat;  
    import java.util.Date;
    public static void main(String[] args) throws ParseException {

        // Current timestamp to string format
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        // Compare
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse("2019-04-15");
        Date d2 = sdformat.parse("2019-08-10");
        System.out.println("The date 1 is: " + sdformat.format(d1));
        System.out.println("The date 2 is: " + sdformat.format(d2));
        if(d1.compareTo(d2) > 0) {
            System.out.println("Date 1 occurs after Date 2");
        } else if(d1.compareTo(d2) < 0) {
            System.out.println("Date 1 occurs before Date 2");
        } else if(d1.compareTo(d2) == 0) {
            System.out.println("Both dates are equal");
        }
    }

--------------------------------------------
**Array List operation**

    List<Integer> al = new ArrayList<>();
    al.add(10);
    al.add(20);
    al.add(30);
    al.add(1);
    al.add(2);
    
    // Remove by index
    al.remove(1);

    // Remove by value
    al.remove(Integer.valueOf(1));

    Iterator itr = al.iterator();
    while (itr.hasNext()) {
        int x = (Integer)itr.next();
        if (x < 10){
            itr.remove();
        }
    }

    // Run backward
    ListIterator<String> listIter = myList.listIterator(myList.size());
    while (listIter.hasPrevious()) {
        String prev = listIter.previous();
        // Do something with prev here
    }

    // Returns true or false
    al.contains(ele);

    // Get element by index
    al.get(index);

    // Find index of element
    ArrayList<Integer> arr = new ArrayList<Integer>(5);
    arr.add(1);
    arr.add(2);
    arr.add(3);
    arr.add(4);
    // using indexOf() to find index of 3
    int pos = arr.indexOf(3);

    import org.apache.commons.lang3.ArrayUtils;
    int[] arr = new int{3, 5, 1, 4, 2};
    int indexOfTwo = ArrayUtils.indexOf(arr, 2);

**Follow this**
    1. remove by index
    2. remove by value
    3. check if element exists
    4. Get element by index
    5. find index of element
    6. ListIterator, move forward and move backward(pass size)

--------------------------------------------
**Misc 1**

    import java.util.HashMap;
    import java.util.Map;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.HashSet;
    import java.util.Set;

    // Initialized to 0, by default.
    int[][] visited = new int[m][n]; 

    // Print multiple numbers
    System.out.printf("%d %d\n", i, j);

    Set<String> hash_Set = new HashSet<String>();
    hash_Set.add("Geeks");
    hash_Set.add("For");
    hash_Set.contains("4") // true or false

    // Hash Map
    if (tempMap.containsKey(ch)){
    }

    // Iterate over a hashmap
    for (Character key : temp.getMyMap().keySet()) {
        Node node = temp.getMyMap().get(key);
    }

    List<Integer> stack = new ArrayList<Integer>();
    stack.add(arr[0]);
    stack.remove(stack.size() - 1);

    stack.add(0, arr[0]);
    stack.remove(0);

    int right = -Integer.MAX_VALUE;

    int item;
    int speed;
    int y = item/speed // java will treat it as int, add 0.0 to treat as double
--------------------------------------------
**Length of different types**

    // For array of integer
    int n = arr.length;

    // For array list
    List<Integer> stack = new ArrayList<Integer>();
    stack.size();

    // For String
    String num1
    int m = num1.length();

---------------------------------------------

The semantics of **LinkedHashMap** are still those of a Map, rather than that of a LinkedList. It retains insertion order, yes, but 
that's an implementation detail, rather than an aspect of its interface.

The quickest way to get the **"first"** entry is still entrySet().iterator().next(). Getting the "last" entry is possible,
but will entail iterating over the whole entry set by calling .next() until you reach the last. while (iterator.hasNext()) { 
lastElement = iterator.next() }

---------------------------------------------
**Misc 2**

    String[] arrOfStr = inpIp.split("\\.");

    for (Map.Entry<IpPreFix, Integer> entry : locationMap.entrySet()) {
        IpPreFix key = entry.getKey();
        Integer value = entry.getValue();
    }

    static int charToNum(char ch){
        return ch - '0';
    }

    static char numToChar(int num){
        return (char)(num + '0');
    }

    // String Buffer
    StringBuffer sBuffer1=new StringBuffer("");
    sBuffer1.append(numToChar(v));
    sBuffer1.reverse();
    sBuffer1.setLength(0);
    return sBuffer1.toString();

    // StringBuffer provides Thread safety but at a performance cost.
    // StringBuilder, is not thread safe so it is faster
    
    int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};

    this.minTime = Math.min(this.minTime, inpStartTime);

    // For Platform problem
    Map<Integer, Integer> myMap = new TreeMap<Integer, Integer>();
    Integer count = myMap.get(inpStartTime);
    if (count == null){
            myMap.put(inpStartTime, 1);
    } else{
            myMap.put(inpStartTime, count + 1);
    }
    myMap.remove(inpStartTime)

---------------------------------------------
**Misc 3**

    if (inputStr.charAt(left) == inputStr.charAt(right));

    // To include last index, add + 1
    return inputStr.substring(maxleft, maxRight + 1);

    import java.util.PriorityQueue;
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    minHeap.add(item);
    first = minHeap.peek(); // do not delete
    first minHeap.remove();
    first = minHeap.poll(); 
    queue.remove("Geeks"); // remove specified element, return True or false

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    List<int[]> queue = new ArrayList<int[]>();
    int[] pos = {i, j, 0};
    queue.add(pos);
    int[] currentPos = queue.remove(0);
    if (0 <= nextI && nextI < m && 0 <= nextJ && nextJ < n);

    for (int[] direction : possibleDirection){}

    for (int item : nums){
        count += (item >> i) & 1;
    }
    single = count % 3;
    result |= single << i;

    // str to int
    int num = Integer.parseInt(ele);

    // int to String
    int i=200;  
    String s=String.valueOf(i);  

    // Array of Char to Str
    char[] arr = { 'p', 'q', 'r', 's' };
    String str = String.valueOf(arr);

    leftMin[i] = Math.min(leftMin[i-1], arr[i]);

---------------------------------------------
**Sample**

    class HelloWorld {
        public static void main(String[] args) {
            System.out.println("Hello, World!"); 
        }
    }

--------------------------------------------
**Parent and Union**

    int findParent(int i, int[] parent){
        while (i != parent[i]){
            parent[i] = parent[parent[i]]; // Set grandparent to parent
            i = parent[i];
        }
        return i;
    }

    Boolean add(int u, int v){
        int x = findParent(u, parent);
        int y = findParent(v, parent);

        if (x != y){
            parent[y] = x;
            return true;
        }
        return false;
    }

--------------------------------------------
**Clone**

class SuperClass implements Cloneable {
	int i = 10;

	@Override
	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}
}

--------------------------------------------
**Old notes**

It is object of Component class
boolean test = (t instanceof Component);
wrapper class have caps String,Short,Long
if statement execpt boolean value,it gives error for in value

If you put a finally block after a try and its associated catch blocks, then once execution
enters the try block, the code in that finally block will definitely be executed except in the following circumstances.

Any method (in this case, the main() method) that throws a checked exception (in this case, out.close() ) 
must be called within a try clause, or the method must declare that it throws the exception.

If a method does not handle an exception, the finally block is executed before the exception is propagated.

if a method throws an exception,and that method is called in a normal block(not in try block) then 
the calling function should have throws delecration.

public class ExceptionTest 
{ 
    class TestException extends Exception {} 
    public void runTest() throws TestException {} 
    public void test() throws Exception
    { 
        runTest(); 
    } 
}

The only legal statements after try blocks are either catch or finally statements.

Error throwable exception runtime can be thrown using throw.

**TreeSet** assures no duplicate entries; also, when it is accessed it will return elements in natural order, which typically means alphabetical.

Two new empty String objects will produce identical hashcodes.

If the equals() method returns true, the hashCode() comparison == must return true.
If the hashCode() comparison == returns true, the equals() method might return true.

arraylist Yes, always the elements in the collection are ordered.

The start() method causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread. 

valid statement
Thread(Runnable r, String name)
Thread()

object class method
notify() notyifyall() wait(long msecs)

Start a thread

class X implements Runnable 
{ 
    public static void main(String args[]) 
    {
        X run = new X(); Thread t = new Thread(run); t.start();
    } 
    public void run() {} 
}

run contain the body of thread.

Even if the thread has finished running, it is still illegal to call start() again.

Runnable(interface), we need to implement it. Define run method. Make an object pass of the class and pass it as arugment to thread class. Call start method thread object.
Thread(class), we need to extend it. Define run method. Make an object of derived class. call start method.

1. An Iterator is an interface in Java and we can traverse the elements of a list in a forward direction whereas a ListIterator is an interface that extends the Iterator interface and we can traverse the elements in both forward and backward directions. 
2. An Iterator can be used in these collection types like List, Set, and Queue whereas ListIterator can be used in List collection only. 
3. The important methods of Iterator interface are hasNext(), next() and remove() whereas important methods of ListIterator interface are add(), next(), hasNext(), hasPrevious() and remove().

**HashMap, TreeMap(keeps the element sortred), LinkedHashMap(keeps insertion order of elements)**

-----------------------------------------
**India Bix**

int [6] myScores; // illegal declartion

if (( ++x > 2 ) || (++y > 2))  // If first condition is true, second condition won't be excuted.

Objects that are equal (according to their equals()) must return the same **hash code**.

If either operand is a String, the + operator **concatenates** the operands.
If both operands are numeric, the + operator **adds** the operands.
