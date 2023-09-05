## Questions

## Annotations in Spring Boot application

What **annotation** need to be added for URL to function mapping in spring boot application?
How do you define the **controller layer**.

**@SpringBootApplication** should be added in main class. This annotation can be used to enable those three features, that is:
    1. **@EnableAutoConfiguration**: enable Spring Boot’s **auto-configuration mechanism**.
    2. **@ComponentScan**: enable @Component scan on the package where the application is located.
    3. **@Configuration**: allow to register **extra beans** in the context or import additional **configuration classes**.

It will fire up a **servlet container** and serve up our service.

## Controller

1. The **@Controller** annotation indicates that a particular class serves the role of a controller. Spring does not require you to extend 
   any controller base class or reference the Servlet API. 
2. The @Controller annotation acts as a **stereotype** for the annotated class, indicating its role. The **dispatcher** scans such
   annotated classes for **mapped methods** and detects **@RequestMapping** annotations.
3. However, the @Controller stereotype also allows for **autodetection**, aligned with Spring general support for detecting component classes
   in the classpath and **auto-registering bean** definitions for them.

1. Spring 4.0 introduced the **@RestController** annotation in order to simplify the creation of RESTful web services. It's a 
   convenient annotation that **combines @Controller and @ResponseBody**, which eliminates the need to annotate every
   request handling method of the controller class with the @ResponseBody annotation.
2. The **@RequestMapping** annotation provides **routing** information. It tells Spring that any HTTP request with the / path
   should be mapped to the **home** method. The **@RestController** annotation tells Spring to render the resulting string
   **directly back** to the caller.
3. The @RestController and @RequestMapping annotations are **Spring MVC annotations**. (They are not specific to Spring Boot.) 

1. The **@RequestBody** annotation maps the HttpRequest body to a transfer or **domain object**, enabling automatic 
   **deserialization** of the inbound HttpRequest body onto a Java object. The type we annotate with the @RequestBody
   annotation must correspond to the JSON sent from our client-side controller.
2. The **@ResponseBody** annotation tells a controller that the object returned is **automatically serialized** into JSON
   and passed back into the HttpResponse object.

## Testing in spring boot

1. The **@SpringBootTest** annotation is useful when we need to **bootstrap the entire container**. The annotation works by 
   creating the **ApplicationContext** that will be utilized in our tests.
2. We can use the **webEnvironment attribute** of @SpringBootTest to configure our runtime environment; we're using 
   WebEnvironment.MOCK here so that the container will operate in a **mock servlet environment**.
3. Next, the **@TestPropertySource** annotation helps configure the locations of properties files specific to our tests. Note 
   that the property file loaded with @TestPropertySource will **override the existing application.properties file**.
4. The application-integrationtest.properties contains the details to configure the persistence storage.

## Java Version Release dates
Java 8  (18 Mar, 2014)
Java 11 (25 Sep, 2018)
Java 17 (14 Sep, 2021)

## Hash SET working

1. How hash set works?
2. In Java, while adding objects to set. How to make sure only **unique value objects** are added.

In hashing there is a hash function that maps keys to some values. But these hashing function may lead to **collision** that is 
two or more keys are mapped to same value. **Chain hashing** avoids collision. The idea is to make each cell of hash table
point to a **linked list** of records that have **same hash function value**.

Let’s create a hash function, such that our hash table has ‘N’ number of buckets. To insert a node into the hash table, we need 
to find the hash index for the given key. And it could be calculated using the hash function. 
Example: **hashIndex = key % noOfBuckets**

**Insert**: Move to the bucket corresponds to the above calculated hash index and insert the new node at the **end of the list**.
**Delete**: To delete a node from hash table, calculate the hash index for the key, move to the bucket corresponds to the 
    calculated hash index, **search the list** in the current bucket to find and remove the node with the given key.

**HashTable** (A **synchronized** implementation of hashing) 
**HashMap** (A **non-synchronized** faster implementation of hashing)

**Overview**
1. HashMap implements **Map** interface while TreeMap implements **SortedMap** interface. A Sorted Map interface is a
   child of Map.
2. HashMap implements **Hashing**, while TreeMap implements **Red-Black Tree**(a Self Balancing Binary Search Tree). 
   Therefore all differences between Hashing and Balanced Binary Search Tree apply here.
3. Both HashMap and TreeMap have their **counterparts HashSet and TreeSet**. HashSet and TreeSet implement Set interface.
   In HashSet and TreeSet, we have only key, no value, these are mainly used to see presence/absence in a set.

Properties of Hashing:
1. Objects that are equal based on the output of **equals() method must have the same hash value**, i.e. they must be mapped
   to the same integer value.
2. Two objects which are not equal **may or may not have same hash values**. Thus, different objects need not have different hash 
   values. 
3. The hash values of objects must remain **consistent** when computed multiple times in the same execution cycle.

**Example**

```java
Set<Book> set = new HashSet<Book>();
Book a = new Book(20, "1234");
Book b = new Book(20, "1234");
System.out.println(a.hashCode()); // 118352462
System.out.println(b.hashCode()); // 1550089733
System.out.println(a.equals(b));  // false
set.add(a);                        
set.add(b);                       
System.out.println(set.size());   // 2
```

In above example, 2 different objects having different address. Two different hashcode will be generated. So they are treated 
as different entitie in HashSet.

Unless you **@Override the hashCode()** method and implement your own version, your class will use the default method
inherited from Object, which generates a **unique hashCode per object instance**.
If you want **equals and/or hashCode** to only care about the members of the object, you'll need to write the 
**implementations yourself**.

## Alternative to make a set of objects whose value is unique.

```java
Set<String> uniqueSet = new HashSet<>(books.size());
Set<Book> uniqueBooks = books.stream().filter(p -> uniqueSet.add(p.getIsbnNumber())).collect(Collectors.toSet());
System.out.println(uniqueBooks.size());
```

## Java Bean

How to create bean?

A JavaBean is a Java class that should follow the following **conventions**:
1. It should have a **no-arg constructor**.
2. It should be **Serializable**.
3. It should provide methods to set and get the values of the properties, known as **getter and setter methods**.

```java
public class Employee implements java.io.Serializable{  
    private int id;  
    private String name;  
    
    public Employee(){}

    public void setId(int id){this.id=id;}  
    public int getId(){return id;}  
    public void setName(String name){this.name=name;}  
    public String getName(){return name;}  
}
```

1. It is a **named feature** that can be accessed by the user of the object. 
2. It may be read, write, read-only, or write-only. JavaBean features are accessed through two methods in the JavaBean's 
   implementation class: **accessor** and **mutator**.

**Advantages**
1. The JavaBean properties and methods can be **exposed** to another application.
2. It provides an **easiness to reuse** the software components.

**Disadvantages**
1. JavaBeans are **mutable**. So, it can't take advantages of immutable objects.
2. Creating the setter and getter method for each property separately may lead to the **boilerplate code**.

## Serialization 

Serialization in Java is a mechanism of writing the state of an **object into a byte-stream**. It is mainly used in 
**Hibernate, RMI, JPA, EJB and JMS** technologies.

The reverse operation of serialization is called **deserialization** where byte-stream is converted into an object. 
The serialization and deserialization process is **platform-independent**, it means you can serialize an object on
one platform and deserialize it on a different platform.

For serializing the object, we call the **writeObject()** method of **ObjectOutputStream class**, and for deserialization we call 
the **readObject()** method of **ObjectInputStream** class.

1. **Serializable** is a **marker interface** (has no data member and method). It is used to **mark** Java classes so that
   the objects of these classes may get a certain capability. The **Cloneable and Remote** are also marker interfaces.
2. The Serializable interface must be **implemented** by the class whose object needs to be **persisted**.
3. The **String class** and all the **wrapper classes implement** the java.io.Serializable interface by default.

If a class has a reference to another class(**Has-A relationship like Student has Address**), all the 
**references must be Serializable** otherwise serialization process will not be performed. In such case, NotSerializableException 
is thrown at runtime.

The **Externalizable interface** provides the facility of writing the state of an object into a byte stream in 
**compress format**. The Externalizable interface provides two methods:
1. public void writeExternal(ObjectOutput out) throws IOException
2. public void readExternal(ObjectInput in) throws IOException

If you **don't want to serialize** any data member of a class, you can mark it as **transient**.

**SerialVersionUID**

1. The serialization process at **runtime associates an id** with each Serializable class which is known as SerialVersionUID.
2. It is used to **verify the sender and receiver** of the serialized object. 
3. The sender and receiver must have the same SerialVersionUID, otherwise, **InvalidClassException** will be thrown when
   you deserialize the object. 
4. We can also declare our **own SerialVersionUID in the Serializable class.** To do so, you need to create a field 
   SerialVersionUID and assign a value to it. It must be of the **long type with static and final**. It is suggested to explicitly declare the serialVersionUID field in the class and have it **private** also.

```java
private static final long serialVersionUID=1L; 
```

## Prevent copy of Singleton class

How to prevent clone of singlten class?

1. **Using Reflection**

```java
class Singleton {
   public static Singleton instance = new Singleton();
   private Singleton()
   {
      // private constructor
   }
}

try {
   Constructor[] constructors = Singleton.class.getDeclaredConstructors();
   for (Constructor constructor : constructors) {
         // Below code will destroy the singleton pattern
         constructor.setAccessible(true);
         instance2 = (Singleton)constructor.newInstance();
         break;
   }
}
```
To overcome issues raised by reflection, **enums** are used because java ensures internally that the 
enum value is **instantiated only once**. Since java Enums are globally accessible, they can be used for singletons. Its only 
drawback is that it is **not flexible** i.e it does not allow lazy initialization. 

2. **Using Serialization**

Suppose you **serialize** an object of a singleton class. Then if you **de-serialize** that object it will create a new
instance and hence break the singleton pattern. 

To overcome this issue, we have to **implement the method readResolve()** method. 

```java
class Singleton implements Serializable {
   public static Singleton instance = new Singleton();

   private Singleton()
   {
      // private constructor
   }
   protected Object readResolve() { 
      return instance; 
   }
}
```

3. **Using Cloning**
**Overide clone function** to throw exception or return the same instance again.

## Java 8 features
1. forEach
2. streams
3. lambda expression
4. Optional class
5. New Date 

[Fix Example](../01_basic/16_clone_singleton.java)

## forEach (Introducted in Java 8)

The forEach() method of ArrayList used to perform the certain operation for each element in **ArrayList**. This method
traverses each element of the Iterable of ArrayList until **all elements have been processed** by the method or an
**exception is raised**. The operation is performed in the order of iteration **if that order is specified** by the method. Exceptions thrown by the Operation are passed to the caller.

You **may send Lambda Expression** as an argument to the “forEach” method, which accepts the 
**Functional Interface** as a single parameter.

Until and unless an overriding class has **specified a concurrent modification policy**, the operation **cannot modify** the underlying source of elements so we can say that **behavior of this method is unspecified**.

```java
ArrayList<Integer> Numbers = new ArrayList<Integer>();
Numbers.forEach((n) -> System.out.println(n));

public static void print(String n)
{
   System.out.println("Student Name is " + n);
}
 ArrayList<String> students = new ArrayList<String>();
 students.forEach((n) -> print(n));
```

## JPA for Spring Boot: Java Persistance API
1. It is an ORM tool (**Object relational mapping**). Object to table mapping. Row is converted to object.
2. JPA is a **specfication** given by oracle. **Hibernate is its implementation**.
3. Other implmentations are **Eclipse Link**, **Open JDA**.
4. H2 is a in-memory DB.
5. **EntityManagerFactory** and **EntityManager** interface provided by JPA to perform **CRUD** operations.
