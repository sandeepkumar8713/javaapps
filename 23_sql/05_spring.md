# Spring vs Spring boot 

https://www.youtube.com/watch?v=zp2tqnpZiAU

## Spring 
1. It is widley used java EE framework for building applications for java platform.
2. It aims to simplify the java EE development and helps developers be more productive at work
3. Unlike other frameworks, spring focuses on several areas of an application and provides a wide range of features.
4. One of the major features of the spring framework is the **dependency injection**. It helps make things simpler 
   by allowing us to develop **loosely coupled applications**.

Spring has multiple module : 
**spring core** to develop web app.
**Spring MVC** 
**Spirng AOP** to manage the cross cutting logic (Aspect orinted programming)
**Spring ORM** or **Spring JDBC**to connect with DB.

## Spring Boot
1. While the spring framework focuses on providing flexibility to you, spring boot aims to **shorten the code length** and 
   provide you with the easiest way to develop a web application. With **annotation configuration and default codes**,
   spring boot shortens the time involved in developing an application.
2. It helps create a stand-alone application with **less or almost zero-configuration**.
3. **Autoconfiguration** is a special feature in spring boot. It automatically configures a class based on that 
   **requirement**.
4. We can hide complexity of J2EE.

**Benefits of Spring Boot over Spring**
1. **Dependency resolution** (In spring we have to resolve dependency of each module version mannually. Idenity modules   
   and match the version.)
   For spring boot we need only 2 dependenceies : spring-boot-strater-web, spring-boot-strater-data-jpa
   No need to mention version.

2. **Minimum configuration** (In spring, we have enable data source, session factory, transcation, mention bean to scan)
   For spring boot, only add few properties in application.properties file to enable.
   spring.datasource.url = ""
   No need for xml file. 

3. **Embededed server for testing**
    @SpringBootApplication
    Put this annotation on main class.
    Run as java application. It will run on your embebed tom cat.

4. **Bean auto scan**
    (In spring we need to manully enable component scan. By specify the root package of the bean. Then container will scan the bean.)

    In spring boot there are 2 ways to enable scan.
    1. decorator method
    Add @ComponentScan(base package) in main class.

    2. Name the package correctly.

    com.javatechie.micrometer.api (main class package)
    com.javatechie.micrometer.api.controller
    com.javatechie.micrometer.api.dao

5. **Health metrics** : (thread count, memory occupied, gc lock)
    (In spring either we need to add customized framework or add third party)
   In spring boot, we have actuator to monitor health.


------------------------------

https://www.java67.com/2015/03/top-40-core-java-interview-questions-answers-telephonic-round.html
https://medium.com/javarevisited/21-spring-mvc-rest-interview-questions-answers-for-beginners-and-experienced-developers-21ad3d4c9b82
https://www.java67.com/2012/08/spring-interview-questions-answers.html
https://www.java67.com/2018/06/top-15-spring-boot-interview-questions-answers-java-jee-programmers.html


## TODO 
1. 01_basic java questions
2. java_notes.md
3. solid
4. design pattern
5. sql
6. spring.md

