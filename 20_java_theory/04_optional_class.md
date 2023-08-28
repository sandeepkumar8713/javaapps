# Optional class

Link : https://www.geeksforgeeks.org/java-8-optional-class/

**Introduced in JAVA 8**

1. **NullPointerException** is very hard to avoid it without using **too many null checks**. So, to overcome this, Java 8 has
   introduced a new class Optional in java.util package. 
2. It can help in writing a neat code **without** using too many null checks. By using Optional, we can specify **alternate**  
   values to return or alternate code to run. This makes the **code more readable** because the facts which were
   hidden are **now visible to the developer**.
3. To avoid **abnormal termination**, we use the Optional class

```java
import java.util.Optional;

public static void main(String[] args)
{
    String[] words = new String[10];
    Optional<String> checkNull = Optional.ofNullable(words[5]);
    if (checkNull.isPresent()) {
        String word = words[5].toLowerCase();
        System.out.print(word);
    }
    else
        System.out.println("word is null");
}
```

1. Optional is a **container object** which may or may not contain a non-null value.
2. If a value is present, isPresent() will return true and **get() will return the value**.
3. Additional methods that depend on the presence or absence of a contained value are provided, such as **orElse()**
   which returns a **default value** if the value is not present, and ifPresent() which executes a block of code
   if the value is present. This is a **value-based class**, i.e their instances are : 
    1. **Final and immutable** (though may contain references to mutable objects).
    2. Considered equal solely based on **equals()**, not based on reference equality(==).
    3. Do **not have accessible constructors**.

## Static method optional class
Static methods are the methods in Java that can be **called without creating an object** of the class. 

1. empty() : Returns an empty **Optional** instance 
2. of(T value) : Returns an **Optional** with specified present non-null value
3. ofNullable(T value) : Return an **Optional** describing the specified value, if non null,
   otherwise returns an empty Optional.

## Instance method 
Instance methods are methods that **require an object** of its class to be created before it can be called. 

1. equals()
2. get()
3. hashcode()
4. IfPresent(Consumer<? SuperT>consumer)
5. IfPresent()
6. orElse(T Other)
7. toString()

## Concrete Methods 
A concrete method means, the method has a **complete definition** but can be **overridden** in the inherited class. If
we make this method **final**, then it can not be overridden. Declaring method or class “final” means its implementation
is complete. It is compulsory to override abstract methods. **Concrete Methods can be overridden** in the inherited classes
if they are not final.

## Example 1

```java
String[] str = new String[5];
str[2] = "Geeks Classes are coming soon";
Optional<String> empty = Optional.empty();
System.out.println(empty); // Optional.empty

Optional<String> value = Optional.of(str[2]);
System.out.println(value); //Optional[Geeks Classes are coming soon]
```

## Example 2

```java
String[] str = new String[5];
str[2] = "Geeks Classes are coming soon";
Optional<String> value = Optional.of(str[2]);
System.out.println(value.get());          // Geeks Classes are coming soon
System.out.println(value.hashCode());     // 1967487235
System.out.println(value.isPresent());    // true
```

## Default And Static Methods In Interfaces

1. You may add **non-abstract methods** to interfaces, allowing you to create interfaces with **method implementation**.
2. To construct interfaces with method implementation, use the **Default and Static** keywords.
3. You may **extend the functionality** of your libraries’ interfaces by using default methods.
4. If a class wishes to alter the default method, it **may override** it and give its own implementation.

```java
interface interface_default {
     default void default_method(){
         System.out.println("We are default method of interface");
    }
}

class derived_class implements interface_default{
 
}

class Main{
   public static void main(String[] args){
        derived_class obj1 = new derived_class();
        obj1.default_method();
    }
}
```
