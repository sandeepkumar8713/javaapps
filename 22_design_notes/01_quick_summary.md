## Quick Summary

**ZooKeeper** 

ZooKeeper is an open source Apache project that provides a centralized service for providing
configuration information, naming, synchronization and group services over large clusters in distributed
systems. The goal is to make these systems easier to manage with improved, more reliable propagation
of changes. (https://www.conduktor.io/kafka/zookeeper-with-kafka/)

ZooKeeper is utilized by several open-source projects to provide a highly reliable **control plane** for distributed 
**coordination** of clustered applications through a **hierarchical key-value store**. The suite of services provided by ZooKeeper include distributed **configuration services, synchronization services, leadership election services, and a naming registry.**

The Kafka team decided to use Zookeeper for this purpose.
Zookeeper is used for metadata management in the Kafka world. For example:

1. Zookeeper keeps **track** of which brokers are part of the Kafka cluster
2. Zookeeper is used by Kafka brokers to determine which broker is the **leader of a given partition** and topic and
   perform **leader elections**.
3. Zookeeper stores configurations for **topics and permissions**
4. Zookeeper sends notifications to Kafka in **case of changes** (e.g. new topic, broker dies, broker comes up, delete topics, 
   etc....)

A Zookeeper cluster is called an **ensemble**. It is recommended to operate the ensemble with an **odd number** of servers, e.g., 
3, 5, 7, as a **strict majority** of ensemble members (a quorum) must be working in order for Zookeeper to respond to requests. 
Zookeeper has a leader to handle **writes**, the rest of the servers are followers to 
handle **reads**.


**How Does OAuth 2.0 Work?**
At the most basic level, before OAuth 2.0 can be used, the Client must acquire its own credentials, a
_client id _ and client secret, from the Authorization Server in order to identify and authenticate itself
when requesting an Access Token.
Using OAuth 2.0, access requests are initiated by the Client, e.g., a mobile app, website, smart TV app,
desktop application, etc. The token request, exchange, and response follow this general flow:

1. The Client requests authorization (authorization request) from the Authorization server, supplying the **client id and secret** to as identification; it also provides the **scopes and an endpoint URI** (redirect URI) to send the Access Token or the Authorization Code to.
2. The Authorization server **authenticates** the Client and verifies that the requested **scopes are permitted**.
3. The **Resource owner** interacts with the Authorization server to **grant access**.
4. The Authorization server **redirects** back to the Client with either an Authorization Code or **Access Token**, depending on the grant type, as it will be explained in the next section. A **Refresh Token** may also be returned.
5. With the Access Token, the Client requests access to the **resource** from the Resource server.

**API Gateway**

Why is an API Gateway used? : An API Gateway serves the following functions:
1. With **authentication** it prevents overuse and abuse of your APIs
2. **Analytics and monitoring** tools can be configured on the gateway itself.
3. It provides a **single endpoint** to external users irrespective of the number of microservices running within your system.
4. Users don’t need to change anything in case of **refactoring**, addition/removal of resources etc as long as the contract remains the same.
5. It also acts as a **traffic controller** by forming a single entry point for all requests.

It generally has the following capabilities:
● **Developer Portal**: This consists of API documentation, testing sandbox, onboarding manuals etc. that helps other developers to use their APIs
● **API Gateway**: This is used to provide a single abstracted layer to the external users
● **API Lifecycle Management**: This manages the design and implementation of all APIs, until it’s deprecated.
● **Analytics**: This helps in deriving insights from the usage and performance of APIs, which can be used as valuable information when designing improvements and 
  extensions.
● **Monetisation**: This helps in generating revenue from your APIs. Contracts can be defined on multiple parameters like scale, usage, number of users etc.

**Disadvantages of API Gateway**
1. **Latency**: The added network hop to the architecture accounts for an increase in latency throughout the system.
2. **SPoF**: The API Gateway being the single entry point for all requests acts as a Single Point of Failure(SPoF). This can be mitigated to some end by having 
   multiple API Gateways and split the calls using Load Balancer and Elastic IP.
3. **Added Complexity**: The API Gateway can get complex when the end users can be of various kinds like IoS, Android, Web, etc. In this case we can add multiple    
   configurations for different entry points. This architecture is also known as “Backend for Frontend” pattern.
