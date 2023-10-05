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
3. **Monolith vs Microservice explain** 
4. **SQL vs NOSQL**
5. How to design **scalable Kafka/Message Queue** system? Suppose we have 30 subscribers. How will you scale when more
   services arrive?(We could use **consistent hashing** to distribute messages.) (by **increasing partition and broker**)
6. How API can be **created** and how **REST** calls can do?
7. Answer **bosscoder** design questions.
8. How to decide on **TTL** of the cache(Plume).
9. **ASGI** for server
10. **HTTPS** working
11. Which **no-sql** to choose based on data?
12. **Vertcial vs horizontal** DB scaling
13. **Advanced python and sql**
14. **Fuzzy problem** (https://www.geeksforgeeks.org/fuzzy-logic-introduction/)
15. **Add all class design code**
16. **UDP vs TCP**, n/w layers
17. Explain how to achievce perfomance in HLD
18. plagiarized how to detect in text.
19. use interface, not so tightly coupled, where we need to pass it as a parameter. (payment type)
    Completly different entity have to do similar stuff.
20. Python vs Java

## Language Specific
1. Add a good **spring** and **spring boot** rest example with **DB, S3 and external api call**.
2. How to write **junit** with mocking?
3. Read about **fluent APIs**.
4. For **principle role**, we should know the language in depth (implementation details of class, hashmap).
5. If we lot of parquet files, to be processed. We can have multiple queues(10) to process them. To map a file to a queue,
   we will use the **metadata of the parquet files**, using this info we can create hash. Since file have 3 columns, which make a unique id. If we hash the metadata. We can map to it a range corresponding to a queue to be processed.

## Design Guru

1. https://levelup.gitconnected.com/i-wish-i-knew-these-12-algorithms-and-their-applications-before-the-system-design-interview-5fb7fa8b1177
2. https://www.linkedin.com/posts/arslanahmad_dbs-activity-7099674975644618752-77uR?utm_source=share&utm_medium=member_desktop
3. https://levelup.gitconnected.com/4-advanced-sharding-techniques-every-software-engineer-must-know-b4493dc6ec0f
4. https://www.linkedin.com/feed/update/urn:li:activity:7098730449920745473?utm_source=share&utm_medium=member_desktop
5. https://www.linkedin.com/posts/arslanahmad_systemdesign-microservices-distributedsystems-activity-7098614581425537025--_Tc?utm_source=share&utm_medium=member_desktop
6. https://medium.com/geekculture/system-design-basics-5-common-caching-misunderstandings-explained-2f19b1c88373
7. https://www.linkedin.com/posts/arslanahmad_systemdesign-distributedsystems-lowlatency-activity-7097177810628186112-Uo7n?utm_source=share&utm_medium=member_desktop
8. https://www.linkedin.com/feed/update/urn:li:activity:7097086824229081088?utm_source=share&utm_medium=member_desktop
9. https://www.linkedin.com/feed/update/urn:li:activity:7065409306275315712?utm_source=share&utm_medium=member_desktop
10. https://www.linkedin.com/feed/update/urn:li:activity:7093120904028684288?utm_source=share&utm_medium=member_desktop
11. https://www.linkedin.com/feed/update/urn:li:activity:7060889009455071232?utm_source=share&utm_medium=member_desktop
12. https://www.linkedin.com/posts/arslanahmad_systemdesign-interview-architecture-activity-7091852560176730113-wN7A?utm_source=share&utm_medium=member_desktop
13. https://www.linkedin.com/feed/update/urn:li:activity:7089615677803651074?utm_source=share&utm_medium=member_desktop
14. https://www.linkedin.com/posts/arslanahmad_blog-design-gurus-activity-7087743902488662016-SJrP?utm_source=share&utm_medium=member_desktop
15. https://www.linkedin.com/posts/arslanahmad_systemdesign-softwarearchitecture-softwaredevelopment-activity-7087411236463644672-h7tN?utm_source=share&utm_medium=member_desktop
16. https://www.linkedin.com/posts/arslanahmad_caching-systemdesign-softwarearchitecture-activity-7087017802510839808-tqi5?utm_source=share&utm_medium=member_desktop
17. https://www.designgurus.io/blog/sys-design-papers
18. https://www.linkedin.com/posts/arslanahmad_select-db-activity-7083056402008576000-lWi2?utm_source=share&utm_medium=member_desktop

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

## How to design system with scalble kafka?
1. https://www.kdnuggets.com/2023/04/build-scalable-data-architecture-apache-kafka.html
2. https://medium.com/startlovingyourself/design-lessons-learned-from-apache-kafka-af26d8d73332
3. https://medium.com/hevo-data-engineering/designing-a-robust-and-scalable-kafka-integration-3dc351e2d92 (detailed one with specific example)
4. https://betterprogramming.pub/system-design-series-apache-kafka-from-10-000-feet-9c95af56f18d (looks best)


https://careers.liveperson.com/job/4950040?source=campaignA&gh_src=&q=Senior%20Software%20Development%20Engineer

You will:
1. Design and develop high-volume, low-latency applications for mission-critical systems and deliver high availability and 
   performance. 
2. Design, build, and deploy Java-based enterprise-level solutions. 
3. Design REST-based API for backend services. 
4. Build new and innovative features from initial concept to release. Maintain and support existing applications and services 
   with uptime on par with industry-standard. 
5. Utilize monitoring tools and logging services to debug and analyze production issues.
6. Evaluate open source tools and frameworks and make recommendations of usage when applicable. Development of technical 
   specifications and documentation. 
7. Coordinate with the data science team to design, build and deploy a machine learning pipeline.

You have:
1. 5+ years experience as a strong Java, Python or NodeJS backend systems developer.
2. 5+ years experience with complex systems designs with large-scale traffic.
3. 5+ years of experience in enterprise systems integration.
4. Ability to effectively communicate designs via flowcharts, sequence diagrams, and similar.
5. Strong computer science fundamentals, including data structures and algorithms.
6. Experience working with data at scale, including Kafka and NoSQL databases.
7. Demonstrated ability to self-learn and grow.

Nice to haves:
1. 5+ years of experience with Java or Python backend applications.
2. 3+ years of experience with cloud-based systems (AWS, GCP, or Azure).
3. Experience with database fundamentals (SQL, indexing, etc.).
4. Experience with multi-cloud security.
