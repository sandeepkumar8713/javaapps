# Twitter 

Youtube : https://www.youtube.com/watch?v=wYk0xPP_P_8

Medium : https://medium.com/@narengowda/system-design-for-twitter-e737284afc95

Geeks for Geeks : https://www.geeksforgeeks.org/design-twitter-a-system-design-interview-question/

## Features :
1. Tweet
2. Timeline: Home, User, Search 
3. Trends

## Load : 
1. Twitter has 300 millions+ user
2. Write : 600 tweets/sec
3. Reads : 600k tweets/sec

## Points :
1. **Read heavy**
2. Eventual consistency : If a person tweets, its okay to have my followers see my tweet after 1-2 secs.
3. Storage : We have to worry about how will we scale and handle lot of reads.

## Storage : 
**DB** : User Table | Tweet Table | Follower Table

**Redis** : Cache entry can be like this:

    <user id >-Tweets :[1, 2, 3 ...]
    <user id>-Followers : [1, 2, 3,.. ]
    T_ID_Tweet "Hey !!!"

Retweet is also consisdered as user's tweet. We can store the retweet with the user so that if original tweet is deleted \
we can have the copy of it.

## Home Time line : 
1. **Simple Approach** : To make a home timeline, fetch the list of all the users current user is following. Get their  
   latest tweets, merge them and display to the user.
1. **Fanout stratergy** : Whenever a user tweets something. Do pre-processing and distribute the data to its follower's 
   home timeline's cache.
2. Compute timeline of people who are online within **last 15 days**. Timeline should also be saved in Redis. 
4. We can't use above startegy for celebrity tweets as they have millions of followers.
5. When a **celebrity** tweets, save it in DB. 
6. In cache, for each user also maintain a **list of celebrities** this user is following. When a normal user want to see   
   his/her timelime, using the above list, **fetch the lastest tweets from user timeline of celebrities**. So now home timeline of normal user will have normal tweets and celebrity tweets as well. All these operations are happening in Redis so it is fast.

## Trends :
Twitter need to find out which hashtags are trending.
1. Trends are calculated based on : **Volume** of tweets and **Time taken** to generate tweets.
3. For example 5K tweets in 5 mins is more interesting than 10k tweets in 1 month.
4. Twitter uses Apache strom / Heron to process streams of tweets. (Kakfa stream can also be used.)

**Trends Working**
Data from one component to another can pass through message queue like **Kafka**. Its better while scaling each component individually.
1. **Filter** : filter out tweets (like graphic/copyright). Most common hashtags are predefined: #Fun, #Food, #quotes
2. **Parse** : It has to figure out hashtags if it is not specified in the tweet. It removes conjunctions and use only 
   important words.  
3. **Count Hashing** : We have to window operation. Tweets with this hashtag in last 1 minute.
4. **Rank** : It gives out rank to each hashtag.
5. **Geolocation** : Since geolocation is slow, we made 2 pipelines. We need to figure out the location of the tweet as all 
   tweets are not relevant globally.
6. **Count Location** : It counts the tweets for each locations for different types of hashtags.
7. **Redis** : From Redis, any API can read the data and show in the application.

## Search timelines : 
1. Early bird. **inverted full text index**. 
2. When a tweet is made, it is broken into words. Each word is saved in a distributed table. Each word refers to all the tweets   
   containing that word. 
3. Twitter has distributed DB. This table is distributed in many data centers. When a search query comes in, it is send to all 
   the nodes of each data centers. They fetch the result from each node, merge them and return back. This is called 
   **scatter and gather** technique.

## Components : 
1. **Http Push Websocket** : To maintain web-socket connection with mobile clients.
2. **Zookeeper** : To coordinate cluster of Redis. Also helps to assign monitor among the cluster. It can be used to name the server 
   automatically. It keeps track of all the nodes in cluster whether it is offline or online. 
3. **Database** : Twitter used Gizzard to save the tweets. Gizzard(**Based on mysql**) was an open source sharding framework to 
   create custom fault-tolerant, distributed databases. It was initially used by Twitter and emerged from a wide variety of data
   storage problems. Gizzard operated as a middleware networking service that ran on the Java Virtual Machine. It managed partitioning data across arbitrary backend datastores, which allowed it to be accessed efficiently.
4. **NoSql** : Twitter uses **Casandra** to run analytics on Big data. 

## Improvements : 
1. Regional distribution of cached data (for Reddis) is done thanks to the Writer API (that writes the tweet in the primary region\
   for Reddis + additional regions).
2. In order to have quick response times between the client and the Write API, the client actually talks to a queue; and the \
   write API picks up messages from that queue.
