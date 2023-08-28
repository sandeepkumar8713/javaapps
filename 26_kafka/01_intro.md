# Kafka Intro 

Link : https://www.kdnuggets.com/2023/04/build-scalable-data-architecture-apache-kafka.html

Apache Kafka is a distributed message-passing system that works on a publisher-subscriber model. It is developed by Apache Software Foundation and written in Java and Scala. Kafka was created to overcome the problem faced by the distribution and scalability of traditional message-passing systems. It can handle and store large volumes of data with minimal latency and high throughput. Due to these benefits, it can be suitable for making real-time data processing applications and streaming services. It is currently open-source and used by many organisations like Netflix, Walmart and Linkedin.

A Message Passing System makes several applications send or receive data from each other without worrying about data transmission and sharing. Point-to-Point and Publisher-Subscriber are two widespread message-passing systems. In point-to-point, the sender pushes the data into the queue, and the receiver pops from it like a standard queue system following FIFO(first in, first out) principle. Also, the data gets deleted once it gets read, and only a single receiver is allowed at a time. There is no time dependency laid for the receiver to read the message.

In the Publisher-Subscriber model, the sender is termed a publisher, and the receiver is termed a subscriber. In this, multiple senders and receivers can read or write data simultaneously. But there is a time dependency in it. The consumer has to consume the message before a certain amount of time, as it gets deleted after that, even if it didn’t get read. Depending on the user's configuration, this time limit can be a day, a week, or a month.

## Components 

Kafka stores the messages in different Topics. A topic is a group that contains the messages of a particular category. It is similar to a table in a database. A topic can be uniquely identified by its name. We cannot create two topics with the same name.

The topics are further classified into **Partitions**. Each record of these partitions is associated with a unique identifier termed Offset, which denotes the position of the record in that partition.

Other than this, there are **Producers** and Consumers in the system. Producers write or publish the data in the topics using the Producing APIs. These producers can write either on the topic or partition levels.

**Consumers** read or consume the data from the topics using the Consumer APIs. They can also read the data either at the topic or partition levels. Consumers who perform similar tasks will form a group known as the Consumer Group.

There are other systems like Broker and Zookeeper, which run in the background of Kafka Server. Brokers are the software that maintains and keeps the record of published messages. It is also responsible for delivering the right message to the right consumer in the correct order using offsets. The set of brokers collectively communicating with each other can be called Kafka clusters. Brokers can be dynamically added or removed from the Kafka **cluster** without facing any downtime in the system. And one of the brokers in the Kafka cluster is termed a **Controller**. It manages states and replicas inside the cluster and performs administrative tasks.

On the other hand, **Zookeeper** is responsible for maintaining the health status of the Kafka cluster and coordinating with each broker of that cluster. It maintains the metadata of each cluster in the form of key-value pairs.

Link : https://medium.com/startlovingyourself/design-lessons-learned-from-apache-kafka-af26d8d73332

**Topic** : You can have multiple topics in given application. Eg: in ecommerce application order events for order data, active products events for new active products, out of stock events for the product which are not available. So we can have 3 different queues/topics here to process given data.

**Partition** : As discussed earlier, incoming stream can be huge, so we split it, and store on multiple nodes. So kafka allows us to setup multiple partition for given topic so that concurrent write happens faster. And also runs replicas for each partition. There is one leader, and remaining slaves. Kafka writes incoming events to the leader partition, and other partitions sync to leader, once it gets completed, kafka send acknowledgement to the publisher. Publisher follow round robin strategy to send events to various partition by feault. You can also configure hase key based distribution, where event with given hashkey always goes to same partition. And we get events ordering at partition level, not at topic level.

**Broker** : Broker is a kafka server, which can have multiple partition running on it. And kafka also runs replicas of broker so that if any broker goes down, kafka still keeps running without any failure of data.

**Cluster** : Kafka runs multiple borkers in kafka cluster.

**Consumer group** : There can be multiple consumers in given consumer group. And they would be reading from multiple partitions for given topic. But all the given event gets processed by one one consumer in same consumer group whereas if we have multiple consumer groups configured for given topic, then each event gets processed by both consumer groups. This allows us to achieve many critical use cases in real time application eg: Lets say you have order streams of the products which we get from customer, now we can run two consumer group for this, the first one process these order, and the second one does analytics on order data. You need to make sure that kafka runs with multiple partitions to achieve better parallelism.


