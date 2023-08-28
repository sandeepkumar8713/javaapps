## Lambda Expression

Link : https://www.geeksforgeeks.org/lambda-expressions-java-8/

**Introduced in JAVA 8**

1. Lambda expressions basically express **instances of functional interfaces**.
2. An interface with a **single abstract method** is called a functional interface. 

**Functionalities**
1. Enable to treat functionality as a method argument, or **code as data**.
2. A function that can be created **without belonging to any class**.
3. A lambda expression can be passed around as if it was **an object and executed on demand**.

```java
interface FuncInterface
{
    void abstractFun(int x);
    default void normalFun(){ System.out.println("Hello"); }
}

class Test
{
    public static void main(String args[])
    {
        FuncInterface fobj = (int x)->System.out.println(2*x);
        fobj.abstractFun(5); // Prints 10
    }
}
```

## Lambda Expression Parameters
There are three Lambda Expression Parameters are mentioned below:
1. **Zero Parameter**

```java
() -> System.out.println("Zero parameter lambda");
```

2. **Single Parameter**

```java
(p) -> System.out.println("One parameter: " + p);
```

3. **Multiple Parameters**

```java
(p1, p2) -> System.out.println("Multiple parameters: " + p1 + ", " + p2);
```

## Example 1 

```java
ArrayList<Integer> arrL = new ArrayList<Integer>();
arrL.add(1);
arrL.add(2);
arrL.add(3);
arrL.add(4);

arrL.forEach(n -> System.out.println(n));

arrL.forEach(n -> {
    if (n % 2 == 0)
        System.out.println(n);
});
```

## Example 2
Here **function is being treated as an object and body of function created dynamically**.

```java
public class Test {
	interface FuncInter1 {
		int operation(int a, int b);
	}

	private int operate(int a, int b, FuncInter1 fobj)
	{
		return fobj.operation(a, b);
	}

	public static void main(String args[])
	{
		FuncInter1 add = (int x, int y) -> x + y;
		FuncInter1 multiply = (int x, int y) -> x * y;
		Test tobj = new Test();
		System.out.println("Addition is " + tobj.operate(6, 3, add));

		System.out.println("Multiplication is " + tobj.operate(6, 3, multiply));
	}
}
```

## Conclusion
1. The body of a lambda expression can contain **zero, one, or more statements**.
2. When there is a **single statement curly brackets are not mandatory** and the return type of the anonymous function
   is the **same as that of the body** expression.
3. When there is more than one statement, then these **must be enclosed in curly brackets** (a code block) and the return type
   of the anonymous function is the same as the type of the value returned within the code block, 
   or **void if nothing is returned**.
