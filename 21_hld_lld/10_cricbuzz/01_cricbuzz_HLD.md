# Cricbuzz 

Youtube : https://www.youtube.com/watch?v=exSwQtMxGd4

Medium : https://medium.com/@narengowda/cricinfo-cricbuzz-system-design-c596e8506669

<u>33.16 Architecture diagram</u>

## Introduction : 
1. Focus on : web user traffic. 2.3 millions per day 
2. We need to push live update instead of pull request. We should have real time on websockets.
3. It is deployed in AWS. 
4. It sends 1 million websockets message in less than 1 secs.
5. 2 strategy to load score in system: 
    1. Use third party which is present on the ground
    2. Cricbuzz employee uploading the scoring manually by seeing the match on tv. We can also use OCR to read score from the TV screen.

## Features : 
Profile, Live score, Calendar, Headlines, Teams, Leader board, photos, support 3rd party like Dreams11, newsTV

## Mixer : 
1. This is a **Read heavy system**. 
2. Main challenge to solve, show score in real time.
    1. Core APIs : Football, Cricket, MotoGP 
    2. Product APIs : 
    3. Mixer or Binder will mix core API or Product API.
3. Send  product api json to the mixer, it will figure out what all core api it needs to call to prepare the result in detail. \
   This will speed up processing as each core API is running in parallel.
4. If one of the core API, either we can make the entire product API as failed.  
   If we sure the client is able to handle partial data, we can still return data from successful core APIs call. 

## Caching : 
1. Cricinfo has **3 layers of cache**. TTL in cache is only 1 sec.   
2. It has 5000 requests/sec. Out of this only 1 will go to the actual core API, remaining will go to Cache. \
   Caching helps to reduce the load on API servers.
3. First level of Cache will be in the product API server itself (**local cache**). cricinfo uses EH cache for local and **memcache** \
   for distributed cache. 
4. The hot APIs will be saved in the local cache itself. So that less traffic is sent in Distributed Cache. 
5. If data is not found in local cache, distributed cache. The it will look into **Varnish**. It will be placed before Core APIs. \
   Varnish is used to save frontend pages. It has request **collapsing feature**. 
6. **Stampede effort** : Similar requests coming at the same time. It will happen at the end of 1 sec (10 milliseconds). \
   When data in local cache and distributed cache is invalidated. Varnish will make 1 request for 100s of similar request \
   and make single call to core API. Then it will serve the call of all the 100 requests.
7. When we have 3 caching layers, so **debugging becomes difficult**. To solve this we can also add **metadata** to the \
   response(time to response, which cache responded).  

## Request Handling : 
1. **Hystrix** : It is a **library** designed to control the interactions between these distributed services providing greater \
tolerance of latency and failure. Properties : **Circuit breaking, default response fallback, request collapse**.
2. If out of 3 core APIs 1 is slow, the response of the product API will be slow. That might cause a **ripple** effect to \
   downstream servers. We can solve this using circuit breaking. 
3. Hystrix will keep monitoring the product APIs. If it detects that response time is slow or too many failures have occurred. \
   It will stop more requests to be send to the product API. This way product API will get time to recover.
4. Default response fallback : It response with stale data if product API is overloaded.

## Logging : 
1. Cricinfo uses **openTSDB (time series database) to monitor** all the API logs. It is high performance DB as it is build on top \
   of Hadoop and Hbase that gives the advantage we can use lot of Hadoop feature. It also highly scalable. 
2. It can take millions of write per secs. It supports Grafana and elastic search. 

## Push based mechanism  : 
1. We need to push the live score automatically to devices.
2. Cricinfo has a system called **Fast Cast**. 
3. We should have a **scheduler**, which wakes up 2 or 3 times in a sec. It has a list of popular APIs. It query these APIs \
   from the product API. Once the scheduler gets the data, it sends it to delta. 
4. **Delta** knows the last data and have the new data. It checks the difference. Now it just puts only the new/updated data \
   in the Redis. **Redis will have n queues for n matches happening live**.
6. That data is pushed to websockets and in turn send to the apps via live websocket connection. 
7. If some devices don’t support websocket they use **long polling**. 
