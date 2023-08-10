# Uber 

Youtube : https://www.youtube.com/watch?v=umWABit-wbk

Medium : https://medium.com/@narengowda/uber-system-design-8b2bc95e2cfe

GeeksforGeeks : https://www.geeksforgeeks.org/system-design-of-uber-app-uber-system-architecture/

Uber : https://www.uber.com/en-IN/blog/engineering/

## Challenges 
One of the main tasks of Uber service is to match the rider with cabs which means we need two different services \
in our architecture i.e.  
1. Supply Service (for cabs)
2. Demand Service (for riders) 

## Dispatch System Work?
DISCO must have these goals…  
1. Reduce extra driving
2. Minimum waiting time
3. Minimum overall ETA

Uber has a Dispatch system (Dispatch optimization/**DISCO**) in its architecture to match supply with demand. This dispatch \
system uses mobile phones and it takes the responsibility to match the drivers with riders (supply to demand). 

**Google S2 Library** : Google has divided the region in a cell of 1 km by 1 km. Each cell is given unique id. When the \
system has to find a taxi within radius of 5 km. It computes which all cell come within the radius and makes a list of \
cell id. Now based on cell ids we have the list of taxis available in the cell. We can filter out and calculate ETA. \
Distance b/w two points are not straight. They travel via road which has turns and traffic. So the ETA given by S2 is \
not accurate. We have to find distance b/w two points via road as well. 

## Components : 
**Supply/Cab Service** : Every 4 sec, taxi sends its location to the server. through a **Web Application Firewall**(WAF). \
Then it hits the load balancer and it goes to **Kafka**. Through WAF we can block IP's, bots, requests from regions not yet serving. 

**Load Balancers** : They are of 2 types. hardware and software. There are different layers of LB. 3 layer based on IPs. \
4 layer DNS based. 7 layer application based.

**Kafka** : Kafka rest, provide API to update location of taxi. That data is sent to **Kafka** and a copy of it is saved in \
**No SQL DB**. Latest location will be sent to DISCO to keep the state machine updated. 

**Websocket** : We need an asynchronous way of sending messages from the client and server (vice versa) at any point of time. \
Whenever there is some **changes happening on either side** the data will be exchanged. It is written in node.js.

**DISCO** : Dispatch optimization component : It is built in node.js. It is very good in asynchronous small mesasaging. \
It is an asynchronous event driven framework. To scale, it does consistent hashing. It uses RPC calls to make calls from \
one server to other server.\
    **Swim protocol** (Gossip protocol) : Here each server knows the responsibility of the other server. We can easily \
    add or remove servers in the ring to increase or decrease responsibility. 

**No Sql** : It can be horizontally scalable. We have write and read a heavy system with almost no down time(during adding new nodes,\
backing up, indexing). It should be highly scalable. To support all this Uber uses **Schemaless** which is built on top of Mysql. \
We can use also these if we don't want to design the DB : BigTable, Canasandra, MongoDB. \
**When a new city is added, it builds a data center near it. If not data will be served from nearset location**. 

**Analytics** : It is making sense of data which we have. \
Hadoop platform/HDFS/HIVE : It has a lot of analytical tool and services like big query tool to get the data which we want from. \
We can learn about **driver and customer behaviour**. Using this we can optimize the system. 

**Map ETA** : It consumes historical data and reads real time data and updates the map. Uber also uses AI simulated trip to calculate \
ETA based on current traffic condition.

**ML fraud detection** : Drivers use GPS based simulation app to create **Fake rides**. This way they get incentive after completing \
25 rides per day. To detect this, system uses historial data to match the ride. Customers can use stolen credit cards for payment. \
Hackers can use compromised credentials. 

**Real time stream analysis** : Spark Strome to figure out trends.

**Logging** : Let all the services keep forwarding logs to a kafka cluster from there logs can go to ELK stack.

## Working:
1. Demand service will request supply service that it needs a cab location of 4 seat cab from a particular location.
2. The supply service knows the cell ids near the requested location. Each region server is responsible for a set of cell \
   ids. The supply server sends the request to the particular region server accordingly.
3. Region server figures out the ETA of the request based on map ETA service. 
4. All this info is sent back to the supply service, which is sent to the client, based on location (whichever is nearest). \
   **The client chooses to book the cab**. 
5. The supply server sends requests (notification) to cab drivers which are nearest to the user. 
6. When a cab driver accepts the request. A can is assigned to the rider. 

## Extra Points:
1. It might happen that, not all cell id info are present on a particular region server. So it forwards the request to the \
responsible server via **RPC**. They all reply to the supply server.
2. **Ring pop** : To handle a new city, we add a region server, which handles request for new cell ids. When a region server is \
removed, its responsibility is passed on to the next server which is free.
3. Uber uses google maps API, to calculate ETA from point A to B. It also includes, cabs whose rides are about to end and are near \
to the source of the rider.
4. **Preferred access point** : Big campus have entry and exit points. It **learns from past history** about these points. As the drivers \
can't the premise.
5. **Total Data center failure**: Uber backs up each component in the backup data center. But the backup data center might not have \
data of the ongoing trip. **In this case, data is fetched from the driver's app and the trip is completed**. 

Draw payment components also.
