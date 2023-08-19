# Golden Rules

1. If we are dealing with a read-heavy system, it's good to consider using a Cache.
2. If we need low latency in the system, it's good to consider using a Cache & CDN.
3. If we are dealing with a write-heavy system, it's good to use a Message Queue for async processing
4. If we need a system to be an ACID complaint, we should go for RDBMS or SQL Database
5. If data is unstructured & doesn't require ACID properties, we should go for No-SQL Database
6. If the system has complex data in the form of videos, images, files etc, we should go for Blob/Object storage
7. If the system requires complex/heavy pre-computation like a news feed, we should use a Message Queue & Cache
8. If the system requires searching data in high volume, we should consider using a search index, tries or a search engine \
   like Elasticsearch
9. If the system requires to Scale SQL Database, we should consider using Database Sharding
10. If the system requires High Availability, Performance, & Throughput, we should consider using a Load Balancer
11. If the system requires faster data delivery globally, reliability, high availability, & performance, we should consider \
    using a CDN
12. If the system has data with nodes, edges, and relationships like friend lists, & road connections, we should consider \
    using a Graph Database
13. If the system needs scaling of various components like servers, databases, etc, we should consider using Horizontal Scaling
14. If the system requires high-performing database queries, we should use Database Indexes
15. If the system requires bulk job processing, we should consider using Batch Processing & Message Queues
16. If the system requires reducing server load and preventing DOS attacks, we should use a Rate Limiter
17. If the system has microservices, we should consider using an API Gateway (**Authentication, SSL Termination, Routing** etc)
18. If the system has a single point of failure, we should implement Redundancy in that component
19. If the system needs to be fault-tolerant, & durable, we should implement Data Replication (creating multiple copies of data \
    on different servers)
20. If the system needs user-to-user communication (bi-directional) in a fast way, we should use Websockets
21. If the system needs the ability to detect failures in a distributed system, we should implement a Heartbeat
22. If the system needs to ensure data integrity, we should use Checksum Algorithm
23. If the system needs to scale servers with add/removal of nodes efficiently, with no hotspots, we should implement \
    Consistent Hashing
24. If the system needs to transfer data between various servers in a decentralized way, we should go for Gossip Protocol
25. If the system needs anything to deal with a location like maps, nearby resources, we should consider using Quadtree, Geohash etc
26. Avoid using any specific technology names such as - Kafka, S3, or EC2. Try to use more generic names like message queues,\
    object storage etc
27. If High Availability is required in the system, it's better to mention that system cannot have strong consistency. \
    Eventual  Consistency is possible
28. If asked how domain name query in the browser works and resolves IP addresses. Try to sketch or mention about DNS \
    (Domain Name System)
29. If asked how to limit the huge amount of data for a network request like youtube search, trending videos etc. One way \
    is to implement Pagination which limits response data.
30. If asked which policy you would use to evict a Cache. The preferred/asked Cache eviction policy is LRU (Least Recently Used) \
    Cache. Prepare around its Data Structure and Implementation.


Link : https://www.tablesgenerator.com/markdown_tables#

| Sl  | Problem / Requirement | Solution |
|-----|-----------------------|---|
| 1   | read-heavy system     | Cache |
| 2   | low latency           | Cache & CDN |
| 3   | write-heavy system    | Message Queue for async processing |
| 4   | ACID complaint        | RDBMS or SQL Database |
| 5   | unstructured & doesn't require ACID properties | No-SQL Database |
| 6   | Complex data in the form of videos, images, files etc | Blob/Object storage |
| 7   | Searching data in high volume | Search index, tries or a search engine like Elasticsearch |
| 8   | Scale SQL Database | Database Sharding |
| 9   | High Availability, Performance, & Throughput | Load Balancer |
| 10  | faster data delivery globally, reliability, high availability, performance | CDN |
| 11  | data with nodes, edges, and relationships like friend lists | Graph Database |
| 12  | High-performing database queries | Database Indexes |
| 13  | bulk job processing | Batch Processing & Message Queues |
| 14  | reducing server load and preventing DOS attacks | Rate Limiter |
| 15  | fault-tolerant & durable | Data Replication |
| 16  | user-to-user communication (bi-directional) | Websockets |
| 17  | detect failures in a distributed system | Heartbeat |
| 18  | data integrity | Checksum Algorithm |
| 19  | scale servers with add/removal of nodes efficiently | Consistent Hashing |
| 20  | transfer data between various servers in a decentralized way | Gossip Protocol |
| 21  | Limit the huge amount of data for a network request | Pagination |
| 22  | Cache eviction policy | Least Recently Used |
| 23  | |     |

## RESHADED :

Educative : https://www.youtube.com/watch?v=uw-gcK9bjkk&t=227s

1) Requirements : Understand and define the boundaries of the system.
2) Estimation : Gauge the scale and size the system will need to handle.
3) Storage Schema : Decide how and where data will be stored and retrieved.
4) High-level Design : Sketch an overall blueprint of how the system will function.
5) API Design : Define how the system will interact with other components.
6) Detailed Design : Dive deeper into each component and flesh out the specifics.
7) Evaluation : Test your design, identify flaws and areas of improvement.
8) Distinctive Component or Feature

## Alternative
1. Polling vs Websocket vs PubSub
