# Reading Material

    1. All the Object Oriented design patterns

    2. 8 LLD designs (Bookmyshow, Uber, Flipkart, Parking Lot, Chess, Imdb, Netflix, Notification)
       7 HLD designs (Bookmyshow, Uber, Whatsapp, Twitter, Tiny url, DoorDash, Cricbuzz, Quora)

    3. API Design (Search Service, File Service, Pub Sub Service, Twitter, Uber)
       Class Diagram (Elevator, Car Rental, Cricinfo)
       SQL query
    
    4. HLD Gloden rules
       LLD Approach
    
    5. Quick Summary (Zookeeper, Oauth2.0, API Gateway)
       LLD details : DB Design, ACID, CAP, API Design, Error Handling (retry), Race condition
       HLD details : Kafka, Redis, Load Balancer, CI/CD steps, Consistent hashing
       REST, Microservice vs Monolith, SQL vs NoSQL
    
    6. SOLID Scalar notes
       Token refresh (Wiki)
       java specific

## Remaining:

How are multiple devices are kept in sync by OTT platform?

LLD : Red bus, Book Online meeting(Outlook), (2)
HLD : Instagram, Netflix (3)
Read : RabbitMQ, UUID (Add in quick summary)
DB types and working
DB design (key value pair DB : https://www.mongodb.com/databases/key-value-database)
CDN
deadlock

Java learn language specific
Educative mail reshaded

Solve ticket booking of same seat at same time
For each LLD write small summary

Byte go : (what questions to ask)
Minimum platform and gold mine
https://www.confluent.io/blog/turning-the-database-inside-out-with-apache-samza/
A lot of discussion around database design and caching mechanism. The interviewer was primarily focussed on API and DB design.
Sample exception handling in rest framework in java

Questions on microservices and distributed systems like: handling race condition in microservics, handling race
condition while updating DB/Cache, how load balancer works and redirects the requests, complete flow of http
request with all touch points etc.

Database questions like: what and why indexing, when do you know if data is to be indexed, how do you index if there
are lot of conditions/filters in data, xss, csrf, sql injection, sql vs nosql, when and why sql/nosql

Designing tiny url with couple of constraints. Further question went on how it can be handled under distributed environment.
caching and improving the db query.

Difference between encryption and encoding. Follow up: A program to encrypt the given string and then decrypt it

Suraj : Microservice, race condition, start from DB, API flow, how to scale, error handling.
queue based, event based. API based (retry). Application bottleneck
