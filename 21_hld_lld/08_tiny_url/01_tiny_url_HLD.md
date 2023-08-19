# Tiny Url

Youtube : https://www.youtube.com/watch?v=fMZMm_0ZhK4

GeeksforGeeks: https://www.geeksforgeeks.org/system-design-url-shortening-service/

## Layers : 
1. API  : **createTiny(longUrl)   getLong(tinyUrl)**
2. Application Layer : How would you generate a tiny url which is unique.
3. Persistence Layer : Where and how would you save the short and long url.

## Components : 
1. Here **Load Balancer** is the frontend for the service. It might be only software, or a combination of s/w and h/w, which does the \
   delegation of service to the worker thread. It also depends on the budget. 
2. **Worker** thread(REST server) would generate the tiny url and save both input & output url in persistence layer. 
3. We will also have caching layer which would be **redis**, **memCache** or any other.
4. **Zoopkeeper**
5. **NoSQL DB**
6. **CDN** 

## Database : 
1. table:  key : tinyUrl and value : longUrl
2. We can we use Nosql like **MongoDB**.

## Tiny URL Generation :  
1. What are the characters we can have in tiny url: A-Z, a-z, 0-9, total : 62.
2. Let tiny URL be only 7 char long. 62^7 = 3.5 trillion combinations
3. If you are doing more requests per second, then you have to increase the number of characters in tiny URL. \
   Here we assume 1000 requests per second.
4. 6 bits are enough to represent a character(62). So 7 characters would need 7*6 = 42 bits to be represented.

## Three possible ways:
1. Generate a **random tiny url** and check in DB. Here we will get different tinyURl for same longURl. So it will be \
   more space consuming and reduce the combination space. There are **3 ways** to detect collision.

    1. Check if tinyUrl is present. If not present, insert in DB.  But the problem is that two worker thread might generate \
       same random thread at the same time and try to insert. (Not good)
    2. To resolve above case use SQL. use query like “**put if absent**”. Use SQL like MySQl and Oracle which supports ACID \
       properties. NoSQL might or might not support “put if absent”. NoSQL scale really well compared to SQL.
    3. **Put** in DB. Do **get** from DB. Verify that this longUrl is same for the given tinyUrl. If not generate a new tinyUrl and \
       try again.

2. **MD5 Approach** : Make a MD5 of longUrl. Use its first 43 bits to make a tinyUrl. (MD5 generates 128 bit long hash). \
   If you take more bits from MD5 hash your probability of collision will be lesser.
    1. Advantage : Here we will get same tinyURl for same longURl as compared to previous method.
    2. Mapping : Convert 43 bits, decimal number(8.79e12) and then convert the number to base 62. Now 0->a, 1->b, ...\
       A->26, B-27 so....

3. **Counter based Approach**: 

    1. **Single host approach** : keep a counterMaintainer service. It will generate and return a unique number to each of the \
       worker thread which requested it. This nunmber will be incremented next time. We will generate tinyUrl using this number. \
       Here we have problem of single **point of failure**. Overloading of this counterMaintainer is also possible.

    2. **All Host** : Here every worker thread internally try to maintain their **own counter**. Suppose we have 64 worker threads.\
       Let us suppose each worker thread have a unique id. So we have 6 bits for that. Take current timeStamp which is 32 bits.\
       Either take random or incremental value of 5 bits. So total we have 43 bits. If you are doing 1000 requests per second. \
       Then each worker thread is doing 20 requests per second. We have random number of only 5 bits(32). So there are chance of collision here.

    3. **Range Based** : 
       1. Lets suppose we have **1 billion possible** combination out of **3.5 trillions** disscussed earlier. 
       2. We divide this into 2000 ranges of **1 million each**. These ranges would be available in counterMaintainer(zooKeeper) \
          which is highly available and durable. 
       3. Worker thread will request for **unused range** from the counterMaintainer and it will internally work within this \
          range. It guarantees that there is no collision. The given out range will be marked used in Zookeeper.  We can \
          easily add new worker thread(**Best**). 
       4. If a worker thread **dies**, we will losse only 1 million \ combination. Once all the ranges are used, counterMaintainer \
          will generate next 1 billion combinations. 
       5. **Security problem** is that, a person can predict the next tinyURl as we have follow a sequence. To overcome this, add \
          **10 more bits to the 43 bits**, and use that to make a tinyUrl(size 9 or 10)

## Points : 
1. Focus here is scalability and durability.
2. If you post tinyUrl on twitter, it will mostly used for first 2 hours. So it’s better to keep it in cache as easy access.   
3. createTiny() can be slow but getLong() should be extremely fast.
4. If you have a global system. **Let the createTiny() go all the way to USA to create it.** Then cache this tinyUrl and longUrl \
in (Content Delivery Network) **CDN** in your local country.
