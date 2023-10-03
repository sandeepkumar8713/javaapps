## Short points

1. Partition topic based on **key or round robin**.
2. But **order of msg** is lost due to partition.
3. **Partition is replicated** in multiple brokers for recovery.
4. **Scale linearly**.
5. Support **batching** of messages and **streaming**
6. different consumer **different speed**
7. **Simple msg format**, each partition is represented by **segement files** saved in Hard disk
8. **push and pull message** both supported
9. **Consumer group** reading from a topic, each consumer consumes from only **one partition** of topic. It can listen to 
   multiple partitions.
10. If a msg is ack, **all previous messages** are also ack
11. Messages are **immutable**.
12. Each consumer, saves **last read** message offset.
13. Broker keeps track of **last offset** of each partition.

## Why Kafka
1. RabbitMQ’s architecture is designed for **complex message routing**. It uses the **push model**. Producers send messages 
   to consumers with  different rules. 
   Kafka uses partition-based design for **real-time, high-throughput** stream processing. It uses the **pull model**. Producers publish messages to topics and partitions that consumers subscribe to.

2. RabbitMQ brokers monitor message consumption. It **deletes messages** after they’re consumed. It supports message priorities. 
   Consumers keep track of message retrieval with an offset tracker. Kafka **retains messages** according to the retention policy. There’s no message priority. 

3. RabbitMQ has low latency. It sends **thousands of messages per second**.
   Kafka has real-time transmission of up to **millions of messages per second**.

4. RabbitMQ supports a broad range of languages and **legacy protocols**.
   Kafka has **limited choices of programming languages**. It uses binary protocol over **TCP** for data transmission

## Kafka Summary

In **summary**, if you get asked if Kafka can replace a database, then here are different answers:

1. Kafka can store data forever in a durable and high available manner providing **ACID guarantees**.
2. Different options to **query historical data** are available in Kafka.
3. Kafka-native add-ons like **ksqlDB** or **Tiered Storage** make Kafka more **powerful** than ever before for data      
   processing and event-based long-term storage
4. **Stateful applications** can be built leveraging Kafka clients (microservices, business applications) without the   
   need for another external database
5. **Not a replacement** for existing databases like MySQL, MongoDB, Elasticsearch or Hadoop. \
   **Other databases and Kafka complement each other**; the right solution has to be selected for a problem; \
   often purpose-built materialized views are created and updated in real time from the central event-base \
   infrastructure
6. Different options are available for **bi-directional pull and push based integration between Kafka and databases** \
   to complement each other

