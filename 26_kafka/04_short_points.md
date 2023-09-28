## Short points

1. Partition topic based on **key or round robin**.
2. But **order of msg** is lost due to partition.
3. **Partition is replicated** in multiple brokers for recovery.
4. **Scale linearly**.
5. Support **batching** of messages 
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

