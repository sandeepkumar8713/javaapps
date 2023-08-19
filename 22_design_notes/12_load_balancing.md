# Load Balancing : 

Medium : https://medium.com/@itIsMadhavan/what-is-load-balancer-and-how-it-works-f7796a230034

## Introduction : 
1. Load balancing refers to efficiently distributing incoming network traffic across a group of backend servers, also known as \
   a server farm or server pool.
2. The technique aims to reduce **response time, increase throughput,** and in general speed things up for each end user.
3. A load balancer acts as the “traffic cop” sitting in front of your servers and routing client requests across all servers \
   capable of fulfilling those requests in a manner that maximizes speed and capacity utilization and ensures that no one server \
   is overworked, which could degrade performance. 
4. If a **single server goes down**, the load balancer redirects traffic to the remaining online servers. When a new server is \
   added to the server group, the load balancer automatically starts to send requests to it.
5. Loads are broken up based on a set of **predefined metrics**, such as by geographical location, or by the number of concurrent \
   site visitors.
6. Members of a certain group — such as **‘people living in Europe’**, for example, may be directed to a server within Europe, \
   while members of another group take, for instance, ‘North Americans’ may be directed to another server, closer to them.

## Load Balancing Algorithms :
1. **Round Robin** — Requests are distributed across the group of servers sequentially.
2. **Least Connections** — A new request is sent to the server with the fewest current connections to clients. The relative 
   computing capacity of each server is factored into determining which one has the least connections.
3. **IP Hash** — The IP address of the client is used to determine which server receives the request.

## Layers 
1. Load balancers are generally grouped into two categories: Layer 4 and Layer 7. Layer 4 load balancers act upon data found \
   in network and transport layer protocols (**IP, TCP, FTP, UDP**). Layer 7 load balancers distribute requests based upon data \
   found in application layer protocols such as **HTTP**.
2. Layer 7 load balancers can further distribute requests based on application specific data such as 
   **HTTP headers, cookies, or data** within the application message itself, such as the value of a specific parameter.

## Session Persistence or Sticky Sessions : 
1. It is essential that all requests from a client are sent to the same server for the duration of the session. This is \
   known as **session persistence**. A method used with Application Load Balancing, to achieve server-affinity.
2. Another use case for session persistence is when an **upstream server** stores information requested by a user in its \
   **cache** to boost performance.

**Dynamic Configuration of Server Groups** :  Many fast‑changing applications require new servers to be added or taken down\
on a constant basis. In such environments, it greatly helps if the load balancer can dynamically add or remove servers from \
the group without interrupting existing connections.

**Hardware vs. Software Load Balancing** : 
1. Load balancers typically come in two flavours: hardware‑based and software‑based. 
2. Vendors of hardware‑based solutions load proprietary software onto the machine they provide, which often uses \
   **specialized processors**. To cope with increasing traffic on your website, you have to buy more or bigger machines from the vendor.
3. Software solutions generally run on commodity hardware, making them **less expensive and more flexible**. You can install \
   the software on the hardware of your choice or in cloud environments like AWS EC2.

## Disadvantages
1. **Latency**
2. **Single point of failure**
3. **Complexity increases**

## Difference

All three are used to optimize and manage web traffic. However, they vary in their function and use cases:

𝐋𝐨𝐚𝐝 𝐁𝐚𝐥𝐚𝐧𝐜𝐞𝐫: A load balancer is a device that distributes network or application traffic across a number of servers to ensure that no single server bears too much demand. This helps to increase concurrency, and reliability of applications by 'balancing' the load among various servers.

𝐑𝐞𝐯𝐞𝐫𝐬𝐞 𝐏𝐫𝐨𝐱𝐲: A reverse proxy, also known as an "inbound" proxy, is a server that receives requests from the Internet and forwards them to a small set of servers, typically located on an internal network. It provides a point of control and can provide security, logging, and even load balancing. The client is unaware it is communicating with a set of servers instead of a single server.

𝐀𝐏𝐈 𝐆𝐚𝐭𝐞𝐰𝐚𝐲: An API gateway is an API management tool that sits between a client and a collection of backend services, acting as a single point of entry for a defined group of microservices. In addition to accommodating direct requests, it can also invoke multiple back-end services and aggregate the results, perform format transformations, handle real-time processing, and implement security policies like OAuth.

𝐃𝐢𝐟𝐟𝐞𝐫𝐞𝐧𝐜𝐞 𝐢𝐧 𝐏𝐮𝐫𝐩𝐨𝐬𝐞:
🔹 A load balancer distributes traffic for the purpose of optimal resource utilization, maximizing throughput, minimizing response time, and avoiding system overload.
🔹 A reverse proxy protects servers from traffic by intercepting requests and managing them.
🔹 An API Gateway, apart from handling requests, also manages and coordinates multiple microservices running behind it.

𝐃𝐢𝐟𝐟𝐞𝐫𝐞𝐧𝐜𝐞 𝐢𝐧 𝐔𝐬𝐞 𝐜𝐚𝐬𝐞𝐬:
🔹 Load balancers are used when there is heavy traffic to servers and we need to ensure the load is evenly distributed.
🔹 Reverse proxies are used to control and protect access to servers in internal networks, manage SSL encryption, or serve static content.
🔹 API Gateways are used in microservices architecture where there are a number of service endpoints and there's a need for functionalities like request routing, composition, and protocol translation.

