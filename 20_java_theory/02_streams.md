## Streams API

Link : https://www.geeksforgeeks.org/stream-in-java/

**Introduced in JAVA 8**

Stream API is used to **process collections of objects**. A stream is a sequence of objects that supports various
methods which can be pipelined to produce the desired result. 

The features of Java stream are:
1. A stream is **not a data structure** instead it takes input from the **Collections, Arrays or I/O channels**.
2. Streams **don’t change the original** data structure, they only provide the result as per the **pipelined methods**.
3. Each intermediate operation is **lazily executed** and returns a stream as a result, hence various intermediate
   operations can be pipelined. **Terminal operations mark the end** of the stream and return the result.
4. A stream consists of a source followed by **zero or more intermediate methods** combined together (pipelined) and
   a terminal method to process the objects obtained from the source as per the methods described.

## Intermediate Operations

1. **map**: The map method is used to return a stream consisting of the results of **applying the given function** to
   the elements of this stream.

```java
List number = Arrays.asList(2,3,4,5);
List square = number.stream().map(x->x*x).collect(Collectors.toList());
```

2. **filter**: The filter method is used to select elements as per the **Predicate passed** as an argument.

```java
List names = Arrays.asList("Reflection","Collection","Stream");
List result = names.stream().filter(s->s.startsWith("S")).collect(Collectors.toList());
```

3. **sorted**: The sorted method is used to sort the stream.

```java
List names = Arrays.asList("Reflection","Collection","Stream");
List result = names.stream().sorted().collect(Collectors.toList());
```

4. **Distinct**: distinct() returns a stream consisting of distinct elements in a stream. distinct() is the method of Stream interface. This method **uses hashCode() and equals() methods** to get distinct elements.

```java
List<Integer> list = Arrays.asList(1, 1, 2, 3, 3, 4, 5, 5);
list.stream().distinct().forEach(System.out::println);
```

## Terminal Operations

1. **collect**: The collect method is used to return the result of the intermediate operations performed on the stream.

```java
List number = Arrays.asList(2,3,4,5,3);
Set square = number.stream().map(x->x*x).collect(Collectors.toSet());
```

2. **forEach**: The forEach method is used to iterate through every element of the stream.

```java
List number = Arrays.asList(2,3,4,5);
number.stream().map(x->x*x).forEach(y->System.out.println(y));
```

3. **reduce**: The reduce method is used to reduce the elements of a stream to a single value. The reduce method takes a 
   BinaryOperator as a parameter.

```java
List number = Arrays.asList(2,3,4,5);
int even = number.stream().filter(x->x%2==0).reduce(0,(ans,i)-> ans+i);
```
