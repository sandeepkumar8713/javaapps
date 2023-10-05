# LLD Approach

Medium : https://thinksoftware.medium.com/how-to-ace-object-oriented-design-interviews-4f9a667e0780

1. When an interviewer asks you this question, the first thing he is looking for is your 
**requirement(functional and non functional)** collection expertise. Whether you can clearly list out all the possible \
requirements and whether you can scope the problem into something that you can solve in 30–45 min.

2. Another thing that the interviewer may be looking for is your design skills, especially object-oriented design. He is \
looking for how well you can design the whole system in terms of components and classes. How you will model the system \
(such as parking lot or an elevator system) into different classes, identify constraints and design interfaces. How \
well-versed you are in object-oriented design concepts like **abstraction, encapsulation, inheritance, and polymorphism**, \
etc. Can you define the **links and associations** between the classes or objects in the system or not?

3. Another thing you need to consider is that when you are designing such a system, you should be discussing the design in terms \
of different **design patterns**. The design patterns are a well-described solution to the most commonly encountered problems 
which occur during software development. These represent the best practices used by experienced object-oriented software 
developers. This shows the interviewer about your experience in object-oriented design and analysis and provides positive feedback to your overall interview performance.

4. The interviewer also looks for whether you can identify different object-oriented design patterns or programming patterns \
while designing the system. Focus on **API and database design** also. Design APIs with **future in mind**. For DB design, focus on **Indexes and normalization**. Prepare to write **SQL queries** as well.

5. Sometimes the interviewer present you with a situation to evaluate how you can tackle **concurrency** in your design. E.g. In a
parking lot design question the interviewer can suggest that the parking lot has two or more entrances or exits.

6. Sometimes he can give you some requirements about the system (e.g. selecting a parking spot near the entrance in the parking lot system design question) to see how you can figure out an **optimal solution** for the problem.

7. This type of question is **not a distributed** system design question and so you should not try to solve it as a distributed \
system design unless you have clearly discussed this with the interviewer in the requirement collection phase. If you try to \
solve it as a distributed system design question, then an interviewer can interpret it as you have not sufficient experience \
to scope a problem and instead of **simplifying**, you are over-complicating the problem. 

## Relationships in Class

Link : https://www.visual-paradigm.com/guide/uml-unified-modeling-language/uml-aggregation-vs-composition/

**UML**
1. Association : arrow, **People have address**
2. Aggregation : white diamond, **Department have people**
3. Composition : black diamond , **Car have Engine**

**Association**
If two classes in a model need to **communicate** with each other, there must be a link between them, and that can be represented 
by an association (connector).

**Aggregation and Composition are subsets of association** meaning they are specific cases of association. In both aggregation and composition object of one class "owns" object of another class. But there is a subtle difference:

**Aggregation** implies a relationship where the child **can exist independently** of the parent. Example: **Class and Student**. 
Delete the Class and the Students still exist.
**Composition** implies a relationship where the child **cannot exist independent** of the parent. Example: House and Room. 
Rooms don't exist separate to a House.

## Uncle bob clean architecture

Link : https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html

## OOPS concept

**Abstraction**
```python
from abc import ABC
class ClassName(ABC):
```

**Encapsulation in OOPS** : (access modifiers, information hiding)
public, protected (single _ variable name start), private(__ double underscore), 

By definition, encapsulation describes bundling **data and methods** that work on that data within one unit, like a class in Java. 
We often often use this concept to hide an object’s internal representation or state from the outside. This is called information hiding.

**Inheritance**

**Polymorphism** (having many forms)
same function in base and child class
