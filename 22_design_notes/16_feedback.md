# Freshworks 

1. Requirement gathering was good
2. No major scalable infra experience/deployment. (**main**)
3. Multi channel support(question)
4. High level design good.
5. Not specific in implmenation.
6. Storage, DB storage diffcult (**main**)
7. Suggested ML modeling which was not required. (**main**)
8. API composition and ack not good (**main**)
9. Can't give alternative (**main**)
10. SQL was average (**main**)
11. Going back and forth (**main**)

## My reflection
1. Discuss non-functional requirements.
2. Do normalization of DB tables.
3. Clear the understing before itself (confusion b/w msg retention and retry)
4. Pratice SQL (**main**)
5. Proper API for authentication. (pass proper values in header) (**main**)
6. Message format should be different for diferent channels like email and SMS.

## Question asked
1. Do reduce DB sql query latency use **indexes and de-nomrmilaztion**. 
2. To handle excpetion we use **try catch block**. Why each module should handle exception **individually**? 
3. What **annotation** need to be added for URL to function mapping in spring boot application?
4. **Monolith vs Microservice explain** 
5. **SQL vs NOSQL**
6. How to design **scalable Kafka/Message Queue** system? Suppose we have 30 subscribers. How will you scale when more
   services arrive?(We could use **consistent hashing** to distribute messages.)
7. How API can be **created** and how **REST** calls can do?
8. Answer **bosscoder** design questions.

pratice sql : https://www.linkedin.com/feed/update/urn:li:activity:7089806957850558464/
https://data36.com/sql-interview-questions-tech-screening-data-analysts/
https://intellipaat.com/blog/interview-question/sql-interview-questions/

template : https://www.linkedin.com/feed/update/urn:li:activity:7089953246693253122/
Rs 250/- book : https://topmate.io/dinesh_varyani/108427
45 mins system design topics to be covered: https://www.linkedin.com/posts/
dinesh-varyani_beavailable-topmatebeyondearth-topmate-activity-7085832468808818689-xa10


## Remaining:

How are multiple devices are kept in sync by OTT platform?

LLD : Red bus, Book Online meeting(Outlook), (2)
HLD : Instagram, Netflix (3)
Read : RabbitMQ, UUID (Add in quick summary)
DB types and working
DB design (key value pair DB : https://www.mongodb.com/databases/key-value-database)
CDN
deadlock

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
