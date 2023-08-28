# New Date API

Link : https://www.geeksforgeeks.org/new-date-time-api-java8/

**Introduced in JAVA 8**

In Java 8, the **Date** class has been **deprecated**.

Drawbacks of old date-time API : 
1. **Not thread safe** : Unlike old java.util.Date which is not thread safe the new date-time API is **immutable**
   and **doesn’t have setter** methods.
2. **Less operations** : In old API there are only few date operations but the new API provides us with many date operations.

Most important classes in **java.time** package them are
1. **Local** : Simplified date-time API with **no complexity of timezone** handling.
2. **Zoned** : Specialized date-time API to deal with various timezones.

## LocalDate/LocalTime and LocalDateTime API 
Use it when time zones are NOT required.

```java
import java.time.*;
import java.time.format.DateTimeFormatter;

LocalDate date = LocalDate.now();
System.out.println("the current date is "+ date); // the current date is 2021-09-23
  
LocalTime time = LocalTime.now();
System.out.println("the current time is "+ time); // the current time is 20:52:39.954238
      
LocalDateTime current = LocalDateTime.now();
System.out.println("current date and time : "+ current); // current date and time : 2021-09-23T20:52:39.956909

DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); 
String formatedDateTime = current.format(format); 
System.out.println("in formatted manner "+ formatedDateTime);
// in formatted manner 23-09-2021 20:52:39
  
Month month = current.getMonth();
int day = current.getDayOfMonth();
int seconds = current.getSecond();
System.out.println("Month : "+month+" day : "+day+" seconds : "+seconds);
// Month : SEPTEMBER day : 23 seconds : 39
  
LocalDate date2 = LocalDate.of(1950,1,26);
System.out.println("the republic day :"+date2);
//the republic day :1950-01-26
  
LocalDateTime specificDate = current.withDayOfMonth(24).withYear(2016);
System.out.println("specific date with "+ "current time : "+specificDate); 
//specific date with current time : 2016-09-24T20:52:39.956909
``` 

## Zoned date-time API 
Use it when time zones are to be considered

```java
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

LocalDateTime date = LocalDateTime.now();
DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
String formattedCurrentDate = date.format(format1);
System.out.println("formatted current Date and"+ " Time : "+formattedCurrentDate);
// formatted current Date and Time : 09-04-2018 06:21:13

ZonedDateTime currentZone = ZonedDateTime.now();
System.out.println("the current zone is "+ currentZone.getZone());
// the current zone is Etc/UTC
  
ZoneId tokyo = ZoneId.of("Asia/Tokyo"); 
ZonedDateTime tokyoZone = currentZone.withZoneSameInstant(tokyo);
System.out.println("tokyo time zone is " + tokyoZone);
// tokyo time zone is 2018-04-09T15:21:13.220+09:00[Asia/Tokyo]
 
DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
String formatedDateTime = tokyoZone.format(format);
System.out.println("formatted tokyo time zone "+formatedDateTime);
// formatted tokyo time zone 09-04-2018 15:21:13
```
