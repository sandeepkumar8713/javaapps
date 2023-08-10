# Netflix

Youtube : https://www.youtube.com/watch?v=psQzyFfsUGU

## Introduction 
1. It has 125 million subscribers in 200 countries
2. **Openconnect** : CDN of netflix. To improve latency, videos are stored in CDN in each country. Less bandwidth is consumed.
   The video is served from the edge server which is nearset to the client.
3. Rest other components are placed in AWS Cloud.
4. Frontend Web app is written by **React.js**

## Components:
Openconnect, Backend and client(desktop, iphone, andriod, ipad, laptop)

<u>Architecture at 4:17</u>

**Elastic Load balancer** : It is balance using round robin. It has 2 tiers
1. level 1 : zones based. 
2. level 2 : instance based.

**Transcoding** : 
1. It converts videos into different formats and is optimized for a particular type of device. Original movie might be of 50 GB. 
2. Netflix creates different files for different n/w speed. For slow network, resolution changes automatically. It is called 
   **adaptive bitrate streaming**.
3. Netflix creates 1k copies of single movies for the same of different resolution. To do so netflix, uses **parallel workers**.
4. The service breaks the movie in chunks and put it in the queue. Now async workers process on each chunk of movie. 
   Now these clips are saved in S3. **Break, process, merge and upload***
5. Now these copies will be placed in each server of **openconnect network**. Login will be done by AWS, service will figure out the   
   best OC server, after that video will play from nearest openconnect. Even while streaming the movie, the service would find 
   best OC server and switch automatically if required.

<u>Video processing flow at 10:30</u>

All the services like login, recommendation, search, billing, home page, customer support are handled by instances which are 
present in AWS cloud. User video viewing pattern is also saved in AWS data centers. Netflix creates ML models based on that data.

## Zuul (gateway service)

<u>Zuul flow at 13:50</u>

1. It provides dynamic routing, monitoring, resiliency and security. It can also be used to do connection management and proxying the  
   requests.
2. First request goes to **Netty proxy**. It is then forwarded to an **Inbound filte**. It can be used for authentication, routing  
   and decorating the requests.
3. The **endpoint filter**: It can return **static response** or it can be forwarded to **backend servers**. The response goes to   
   the **outbound filter**. It will gzip if required. To calculate matrix. To remove /add headers.

**Advantages** 
1. Share the traffic : Send similar request from endpoint filter to backend server.
2. Load testing : We can redirect a part of requests to new set of servers.
3. Test new service : We can deploy a new service to only few servers. Then redirect some part of traffic to these servers.
4. Filter bad request : Like requests from 1 particular device.

## Hystrix

<u>Hystrix at 16:50</u>

1. It is latency and fault tolerant **library** designed to isolate point of access to the remote node.
2. Suppose 1 endpoint, inturn makes several calls. Among those calls if 1 service is slow, then the slowness will cascade 
   to endpint call.
3. It is a **decorator** placed around each microserivce. 

**Advantages**
1. **Timing out call** > time: If a service is taking longer to respond, Hystrix will gracefully kill the request
2. Reject req when **thread pool is full**. Hystrix won't accept more calls. It will straight way reject the call.
3. Disconnect the service when **error rate** is higher than threshold.
4. Fallback to **default response**.
5. Metrics : Gathers data on latency, performance and puts in a dashboard.

## Microservice
1. **Critical Service** : Even if many components are down. This service should provide basic features. Make sure it is highly 
   reliable. We can separate out critical APIs to have less dependency. (Like search, navigate to fav video, play the video)
2. **Stateless** : If a server is giving error, we can switch back to another server. State should be preserved in cache of 
   particular server.

## EV Cache:

<u>Hystrix at 25:21 </u>

1. It is build by Netflix. It is based on memcache. 
2. They have deployed lot of clusters on number of EC2 instances. On which there are many nodes of memcahce. Each cluster have their
   own cash client.
3. When a write happens, the data is **written** on all the clusters **distributed network** by cache client. The read happens from 
   the nearby cluster. They use **SSD** instead of RAM.
4. Netflix heavly uses EV cache for all of its endpoints.

**Advantages** : 
1. It's **throughput** will be higher
2. **Latency** will be less. 
3. **Cost** less requirement to deploy API or endpoint servers.

## Database 

<u>Database at 28:54 </u>

**MySql** : 
1. To save user profile, transaction and billing. Read is heavy here.
2. **Master <->  Master (backup master)**. Once write is done both masters, then only ack for commit is sent.
3. In case of master failure, write is redirected to second master node.
4. **3 slaves** (at local, acroccs DC) for reading. This makes it highly available.

**Cassandra** : 
1. It is open source, distributed, Nosql, schemaless, handle large out of data on any commodity server. It can also handle heavy read 
   and write.
2. Netflix saves user view history, big data history, search history, interests viewing pattern.
3. Write to read ratio is 9 : 1
4. Netflix optimized cassandra for more **write operation**.
5. When lot of data is segregated, run a scheduled job, which divides the date in 2 sections : 
   1. Living viewing history : This data is retained
   2. Compressed view history : Push it to another cassandra node in zip format.

## Kafka and Chukwa:

<u>Kafka and Chukwa at 32:58 </u>

1. Netflix produces 500 billions events, 13 PB everyday.
2. The data can be :
    1. Video viewing activity 
    2. UI activity 
    3. Error Logs
    4. Performance events
    5. Trouble shooting events
3. **Apache chukwa** is an open source data collection system for collecting logs on events from distributed system. Which is built on 
   top of **HDFS or map reduce framework**. 
4. It also has hadoop **relabiltity and robustness**. It has a lot of tool kits for displaying and monitoring purpose.
5. From Chuwka, we can see the dashboard. We can do monitoring, analysis. It can also feed to S3 and kafka.

**Message router** : 
1. We can apply filters here. 
2. Front end kafka pass data to secondary kafka. Like Elastic search and spark.
3. This routing is done by **Apache sams framework**. Kafka routers consume from one kafka topic and produce in another 
   kafka topic. We can apply filter here also.

**Elastic search** : 
1. 150 cluster, 3500 instances
2. Used for customer support, trace log.
3. We can search all the logs of particular users. These help in troubleshooting.
4. It is used to see error in signup. Keep track of resource usage.
5. Visualization is through Graphana or Kibana.

## Recommendation

**Spark**:
1. It is used for **recommendation** and personalization. By making **ML models** on large spark cluster.
2. These models are used for **sorting**, row selection(different types of genre) and relevance rank.
3. So the user has a recommendation page when the frontend is loaded.

**Artwork and recommendations**: 
1. Header image : thumbnail of movies. Netflix needs to choose a best header image.
2. Initially, Netflix generates 9 images, shows them to different users, generates usage data and calculates which 
   header generates the most hits. Thats how it's chosen. (**train & learn**)

**Movie recommendation factors**: 

    Interaction with service : How user is interacting with the service
    Other user’s taste
    Metadata like title, director actor, genre
    Device
    Time of day

1. **Collaborative filtering** : If 2 clients have similar rating history they both might like the same movie.
2. **Content based filtering** : Show similar movies the person have liked before.

## Open connect : 

<u>OC at 44:37</u>

1. It caches only **video**. It is designed for netflix only.
2. **Free** of charge (only for qualified ISPs)
3. Highly available with **redundant components**. (2 power supply)
4. Customer gets better quality of video as the OC is **within n/w domain** of ISP. **Bandwidth consumption** is less.
5. Currently Netflix is serving 125 millions hours of traffic **10 TB/sec**
6. Different types of OC services
    1. **Small OC** given to Small ISPs. Cache only popular videos
    2. **Big OC** given to big ISPs. Cache all videos.
7. Some contents are world wide popular, so put them on all servers. Some contents are **specific to countries**, put them 
   in regional servers.
8. Caching is done based on historical **viewing patterns** and popular content. If a person is watching **episode 1**, the episode 2 
   will also be seen shortly. So cache it in those location within that region.

**How video distribution to all OC happens**
It uses **consistent hashing**. Hash the filename. Go to that node in the cluster based on the hash produced. If the data is 
not present in cache, request will be forward to main server and cached locally.

**Miscellaneous**
1. SNS, Route 53 (dns resolution), SQS, **Amazon auto scale** (to expand infrastructure) 
