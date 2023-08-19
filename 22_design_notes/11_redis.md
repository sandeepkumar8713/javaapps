# Redis (Remote Dictionary Server)

Medium : https://medium.com/@juwelariful1/what-is-redis-and-why-with-use-case-1b294b91e373

## Introduction : 
1. AWS says that Redis is a fast, open-source, in-memory **key-value data store** for use as a 
   **database, cache, message broker, and queue**. 
2. Redis is also known as **NoSQL Database** and key/value store.
3. An in-memory database is a database that keeps the whole dataset in RAM. What does that mean? It means that each \
   time you query a database or update data in a database, you only access the main memory. So, there’s no Disk involved \
   in these operations. And this is good, because the main memory is way faster than any disk.
4. key-value database, or key-value store, is a data storage paradigm designed for storing, retrieving, and managing \
   associative arrays, and a data structure more commonly known today as a dictionary or hash table.
5. Redis has **built-in replication**, Lua scripting, **LRU eviction**, **transactions**, and different levels of \
   **on-disk persistence** and provides **high availability** via Redis Sentinel and **automatic partitioning** with Redis Cluster.


## Use Cases : 
1. **Session Cache** : Many websites leverage Redis Strings to create a session cache to speed up their website experience \
   by caching HTML fragments or pages. Since data is stored temporarily in the RAM, this attribute makes Redis a perfect \
   choice as a session cache.
2. **Full Page Cache** (FPC) : Redis provides a very easy FPC platform to operate in. Magento offers a plugin to utilize \
   Redis as a full page cache backend. 
3. **Queues** : Any application that deals with traffic congestion, messaging, data gathering, job management, or packer \
   routing should consider a Redis Queue, as this can help you manage your queue size by the rate of arrival and departure \
   for resource distribution.
4. **Leaderboards/Counting** : Redis is a popular choice among **game developers** looking to build real-time leaderboards. \
   Simply use the **Redis Sorted Set data structure**, which provides the uniqueness of elements while maintaining the list \
   sorted by users’ scores. Creating a real-time ranked list is as easy as updating a user’s score each time it changes. \
   You can also use Sorted Sets to handle time-series data by using **timestamps as the score**.
5. **Pub/Sub** : Redis supports the use of publish and subscribe (Pub/Sub) commands, users can design high-performance chat \
   and messaging services across all their applications and services. This includes the ability to use list data structures \
   to run atomic operations and blocking capabilities.
6. **Realtime analysis**: Redis can process data with **sub-millisecond latency**, it is ideal for real-time analytics, online \
   advertising campaigns, and AI-driven machine learning processes.

## Advantages  : 
1. **Quick response database** : it stores data in memory, rather than on a disk or solid-state drive (SSD) that's why its \
   response time is quicker than others when performing read and write operations.
2. **Data persistence** : Redis uses persistent disk storage designed to survive process outages and network bottlenecks. Redis \
   can persist datasets by taking regular snapshots of data and appending them with changes as they become available. Redis can \
   then be configured to generate these database backups on-demand or at automatic intervals to ensure database durability an integrity.
3. **Support for arbitrary data** : Data stored in Redis can be in any form and size. Redis is binary-safe so it can store any \
   data, from human-readable text to encoded binaries. A single data element in Redis can range in size from 0 bytes to 0.5GB, \ 
   allowing it to cache practically any datum.
4. **Key-based access**: Redis is based on the key-value model in which data is stored and fetched from Redis by key. Key-based \ 
   access allows for extremely efficient access times and this model maps naturally to caching, with Redis providing the customary\
   GET and SET semantics for interacting with the data.
5. **Data expiration** : Keys in Redis can be set with a time to live (TTL), after which they are expired. Until they expire, \
   such keys are called “volatile” keys.
6. **Developer friendly** : Redis is being supported in most of the languages (Perks of using an Open Source Technology). \
   Languages like JavaScript, Java, Go, C, C++, C#, Python, Objective-C, PHP and almost every famous language out there has support for this.
7. **Extensibility** : Redis is an open-source project supported by a vibrant community. There’s no vendor or technology lock-in\
   as Redis is open standards-based, supports open data formats, and features a rich set of clients.


## How Redis expires keys :
Redis : https://redis.io/commands/expire/#how-redis-expires-keys

1. Redis keys are expired in two ways: **a passive way, and an active way**.
2. A key is passively expired simply when some client tries to access it, and the key is found to be timed out.
3. Of course this is not enough as there are expired keys that will never be accessed again. These keys should be expired anyway, \
   so periodically Redis tests a few keys at random among keys with an expire set. All the keys that are already expired are \
   deleted from the keyspace.
4. Specifically this is what Redis does **10 times per second**:
    1. Test **20 random keys** from the set of keys with an associated expire.
    2. Delete all the keys found expired.
    3. If more than **25% of keys were expired**, start again from step 1.
5. This is a trivial probabilistic algorithm, basically the assumption is that our sample is representative of the whole key space,\
   and we continue to expire until the percentage of keys that are likely to be expired is under 25%
6. This means that at any given moment the maximum amount of keys already expired that are using memory is at max equal to max 
   amount of write operations per second divided by 4.

The expiration cycle has been rewritten in Redis 6.0 to allow for much faster expirations that more closely match the time-to-live \
(TTL) property. Redis 6 expiration will no longer be based on random sampling but will take keys sorted by expire time in a **radix tree**.

## Caching Algorithm :
1. **Least Frequently Used** keeps track of how often a cache entry is accessed. The item that has the lowest count gets \
   removed first.
2. **Least Recently Used** puts recently accessed items near the top of the cache. When the cache reaches its limit, the least \
   recently accessed items are removed.
3. **Most Recently Used** removes the most recently accessed items first. This approach is best when older items are more likely \
   to be used
