## 1. Distributed Cache

[Link](../../systemdesign/03_High_level_design/19_distrubuted_cache/01_intro.md)

caching system where multiple cache servers coordinate.
**precalculating results, expensive queries, session data, reduce network costs**

**Writing policies**
Write-through cache(both on db and cache), Write-back cache(first in cache, async in DB), Write-around cache(only in DB) 

**Eviction policies**
Least recently used (LRU), Most recently used (MRU), Least frequently used (LFU), Most frequently used (MFU), first in, first out (FIFO)

**Cache invalidation**
Active expiration, Passive expiration

**Hash function**
It’s possible to use hashing in two different scenarios:
1. **Identify the cache server** in a distributed cache to store and retrieve data. (**consistent hashing**)
2. **Locate cache entries** inside each cache server. (**typical hash functions**)

**doubly linked list**, widespread usage and simplicity. Furthermore, adding and removing data from the doubly linked 
list in our case will be a **constant time operation**.

**Sharding in cache clusters**
1. Dedicated cache servers
2. Co-located cache

A cache client is a **piece of code residing** in hosting servers that do **(hash) computations** to store and retrieve
data in the cache servers.
1. Each cache client will know about **all the cache servers**.
2. All clients can use well-known transport **protocols like TCP or UDP** to talk to the cache servers.

**FRs**
Insert data, Retrieve data

**API design**
insert(key, value), retrieve(key), delete(key)

![](images2/01_cache_detailed_hld.png)

**configuration service** that continuously **monitors** the health of the cache servers. In addition to that, the cache clients 
will get **notified when a new cache server** is added to the cluster. When we use this strategy, **no human intervention** or 
monitoring will be required in case of failures or the addition of new nodes. Finally, the **cache clients obtain the list** of 
cache servers from the configuration service.

**Distinctive Points**
hotkey problem(replicas), Storage hardware(specialized or commodity hardware, secondary storage)

## 2. Distributed Queue

**FRs**
Queue creation, Send message, Delete message, Queue deletion

**Message Ordering**
Best-effort ordering : messages will be put in the queue in the same order they were **received**
Strict ordering : messages are placed in a queue in the order that they’re **produced**.
Monotonically increasing numbers, Causality-based sorting at the server side, Using time stamps based on synchronized clocks

**Effect on performance**
monotonically increasing : **time-window** We have to **sort messages** received within a specific **time frame**.
strict ordering : at the receiving end, we need to **serialize all the requests** to give out messages one by one

**Managing concurrency**
Concurrent queue access needs proper management. Concurrency can take place at the following stages:
1. When multiple messages arrive at the same time.
2. When multiple consumers request concurrently for a message.
Solution : **locking mechanism**, **system’s buffer(OS handles it with a single thread)**

![](images2/02_queue_hld.png)

**Front-end service**
Request validation, Authentication and authorization, Caching, Request dispatching, Request deduplication, Usage data collection

**Metadata service**
This component is responsible for **storing, retrieving, and updating the metadata** of queues in the metadata store and cache. 

The **first strategy** is to use the **sharding approach** to divide data into different shards. Sharding can be performed based
on some **partition key or hashing** techniques, as was discussed in the lesson on database partitioning. Each **shard** is stored
on a **different host** in the cluster. Moreover, each shard is also **replicated on different hosts** to enhance availability. In this 
cluster-organization approach, the **front-end server has a mapping table** between shards and the hosts. Therefore, the front-end 
server is responsible for redirecting requests to the host where the data is stored.

The **second approach** is similar to the first one. However, the mapping table in this approach is stored **on each host** instead
of just on the front-end servers. Because of this, **any random host** can receive a request and **forward** it to the host where the 
data resides. This technique is suitable for **read-intensive** applications.

In the primary-secondary model, each node is considered a **primary host** for a collection of queues. The responsibility of a
primary host is to **receive requests** for a particular queue and be **fully responsible for data replication**. 

**A cluster of independent hosts** : Each host consists of **mapping between the queues and the hosts** within a cluster, making the 
replication easier. When a random host receives a message, say host C, for a queue having ID 103, host C **replicates** this message on 
the other hosts where the queue **103 is stored, i.e., Node A and Node B.**

There are two ways to replicate messages in a queue residing on multiple hosts.
1. Synchrounous replication
2. Asynchrounous replication

1. A job can then **delete the message when the expiration conditions are met**.
2. However, it’s made **invisible** for some time via an attribute—for example, attribute—for example, visibility_timeout. This way, the 
   other consumers are unable to get messages that have already been consumed. The message is then deleted by the consumer via an **API call**.
In both cases, the message being retrieved by the consumer is only **deleted by the consumer**. Moreover, this approach also provides 
**at-least-once** delivery semantic

## 3. Pub Sub

**FRs**
Create a topic, Write messages, Subscription, Read messages, Specify retention time, Delete messages

![](images2/03_pub_sub_detailed_hld.png)

**API design**
create, write, read, subscribe, unsubscribe, delete_topic

**Components**
Brokers : handle write and read requests, multiple partitions, specific offset address
To maintian strict order of messages : The user can provide the partition_ID while writing into the system.

We’ll allocate the **partitions to various brokers** in the system. We’ll follow strict ordering in partitions by adding newer content at
the **end of existing messages**.

Since these are **immutable records**, the **readers are independent** and they can read messages **anywhere** from this file using
the necessary API functions.

**Consumer manager**
Verify the consumer, Retention time management, Message receiving options management, Allow multiple reads

## 4. Distributed Search

**FRs**
Search: Users should get relevant content based on their search queries.

**Inverted index**
For each term, the index computes : list of documents, frequency, position
Pros : full-text searches, reduces the time of counting the occurrence
Cons : storage overhead, Maintenance costs

![](images2/04_simple_hld.png)

**API**
search(query)

We employ a large number of **low-cost machines (nodes) and partition or divide the documents** based on the resources they have.
1. **Document partitioning** : each query is distributed across all nodes, and the results from these nodes are merged before 
   being shown to the user. This method of partitioning necessitates **less inter-node communication**. 
2. **Term partitioning** : a subset of documents is processed and indexed by a node containing the term “search.”
   **Multiword queries necessitate sending long mapping lists between groups of nodes for merging**, which can be more 
   **expensive** than the benefits from the increased concurrency.

**Replication**
1. We make **replicas of the indexing nodes that produce inverted indices** for the assigned partitions. 
2. **Each group of nodes is hosted on different availability zones** for better performance and availability of the system in  case a 
   data center fails. 
3. A replication factor of three means three nodes host the same partition and produce the index. One of the three nodes becomes the 
   **primary node**, while the other **two are replicas**. Each of these nodes produces indexes in the same order to **converge** on the 
   same state.
4. The **load balancer chooses one of the three copies of each partition** to perform the query

**Better Solution**
We **compute the inverted index on the primary node only**. Next, we **communicate the inverted index** (binary blob/file) to the 
replicas. 

![](images2/04_map_reduce.png)

Note that the Reducers cannot start as long as the Mappers are working. This means that the **cluster manager can use the same node as a Mapper as well as a Reducer**.

The **indexing is performed offline**, not on the user’s critical path.
So, **we don’t have to wait for the replication of the new index** to respond to the search queries.
The **strong isolation of indexing and search processes** help indexing and search scale independently and dynamically.
We utilized a number of nodes, each of which performs **search queries in parallel** on smaller inverted indices.


## 5. CDN

![](images2/05_cdn_hld.png)

