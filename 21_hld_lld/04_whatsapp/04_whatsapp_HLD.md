# Whatsapp 

Youtube : https://www.youtube.com/watch?v=L7LtmfFYjc4

## Features  
1. User base (count)
2. Last seen
3. Support to send media messages
4. Encrypt our messages
5. Acknowledgment 
6. Telephony service (Not supported in this)
7. Group Message (Not supported in th is)

## Components 
**Load Balancer**

**Message server**

**Web socket**
We need duplex connection here as the channel b/w client A and Client B as anyone can send message at any time. 

**DB** 

## Working Steps
1. When the message from client A goes to message server via **load balancer**. The message server checks if client B is online.\
If yes, it will send the message to client B then and there. If not, then it will save the message in DB and wait for \
client B to come online.

2. Please note, here client will connect to server as server itself doesn't know the address of the client. 

3. **Acknowledgment : send, delivered, read**
Send: Once server receives the message, it sends an ack to client A saying message has reached the server. \
delivered: Once message is sent to Client B, the server sends an ack to client A saying message has been devlivered to B. \
Read: Once client B has seen the message, the client B sends ack that ack saying message read to server. Server sends ack \
to client A.

4. When a connection is established b/w client A and server. A process is created in the server for every client. It is \
responsible for handling messages for client A. It is a **long running thread**. There will a queue for each process. \

5. We will add an entry in DB with columns: **Process id and user id**. 

6. When process A, receives a mesasage for client B. It queries the DB, to find process id of client B. Now process A will send the \
message to the queue which is responsible to handle process for client B.

7. The process for client B, keeps on looking for messages in queue and as message comes in queue, it sends the message to client B.

8. If client B is not connected to message server. There will not be any process or queue for B. So now client A, will save the \
message for B in DB with entry as : **User id and message**. When connection with client B is established, a new process is \
created for same and entry for same is created in DB. The process B also looks for any undelivered message in DB and delivers \
it to the client B.

## Other Features
1. **Last seen** : We need **heart beat signal**. For this we need an entry in DB : user id, last seen timestamp. \
While the connection b/w client A and message server is alive, **every 5 secs** the client sends hearbeat to the server and \
the timestamp is updated in the DB. When clients want to know the last seen of other clients, the message service can \
query the DB to find out the last seen.

2. **Media** : The will not use websocket to send media messages. We send media message using CDN and http server. When client B \
sends a picture, it is sent to HTTP server and saved in CDN. The http server will return unique message id for the uploaded image. \
So now client B will send only message id and type of media to the message server. Now client A, will use the message id to download \
the message from http server. 

3. **Encryption** : 2 ways to encrypt the message. Use a single key to encrpyt and decrpyt the message. (Symmetric encryption). \
Public and private key to encrpyt and decrpyt the message. (Asymmetric encryption)

## Technologies
1. **Erlang** : Pretty fast, really good performance, update the code on the fly, it supports light weight thread. \
(Whatsapp supports **10 million connections in one message server**. We should know the system in and out for this. It can done if we \
are able to **tweek kernel of the server and n/w libraries**.)

2. **FreeBSD** : It is the OS used in message server, as the developer knew the system in and out so that they can get \
maximum throughput. 

3. **Mnesia** : Mnesia is a distributed, soft real-time database management system written in the Erlang programming language. \ 
It support **ACID properties**. It works great with Erlang. Mnesia is an RDBMS. But it belongs to the NoSQL category of RDBMS. \
Reason being the **query language is not SQL but Erlang**. That makes it very easy to store and retrieve without having to go \
through Object Relational Mapping. 

4. **Yaws** (Yet another web server) : is a web server written in Erlang by Claes (klacke) Wikström. Yaws can be embedded into other \
Erlang-based applications or run as a regular standalone web server.


## Improvements:
1. We can use **indexing on UID** for faster retrievals. O(n) to O(log(n))
2. Spawning a thread for every user is not efficient. Instead, we can use a **dynamic threadpool**.
3. Similarly, having a queue per user is not efficient. Instead, we can have a **global queue** with object (message, action \
like send/receive, uid)
4. Since you are using DB for storing message in case user is not online, we need to implement a disaster recovery mechanism \
i.e. **replication**.
5. We can also implement **blocked contacts** by storing list of blocked UIDs for each user in the DB & we can drop such messages \
in web server.

