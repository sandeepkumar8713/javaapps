## NFRs
Scalability, Security, Consistency, Availability, Performance

## 1. Quora

**FRs**
Questions and answers, Upvote/downvote and comment, Search, Recommendation system, Ranking answers

**API**
postQuestion, postAnswer, upvote, comment, search

![](images/01_quora_hld_detail.png)

**Distinctive points**
Vertical Sharding, Disaster recovery

## 2. Google Maps

**FRs**
Current Location, Recommend the fastest route, Give directions

**Challenges**
Scalability, ETAs

![](images/02_gmap_hld.png)

**API**
currLocation, findRoute, directions

**Distinctive points**
Segments(Storage schema of each segement), Graph DB, Websocket, pubsub, Cache, analytics, livelocation

## 3. Yelp

**FRs**
User account, search, Feedback

**API**
addPlace, addReview

![](images/03_yelp_design.png)

**Storage Tables**
SQL : Place, Photo, Reviews, Users

**Distinctive points**
Searching(introduce segements), Cache(for faster fetch), Data partition based on Region or place_id, Ensure Availability by replicating 
quad server.

## 4. Instagram

**FRs**
Post photos and videos, Follow and unfollow users, Like or dislike posts, Search photos and videos, Generate news feed

**APIs**
postMedia, followUser, likePost, searchPhotos, viewNewsfeed

![](images/04_insta_design.png)

**Storage**
SQL : Users, Followers, Photos, Videos

**Distinctive points**
Timeline Generation(Pull, push, Hybrid approach)

## 5. Type Ahead

**FRs**
Top n frequent and relevent words

**APIs**
getSuggestions, addToDatabase

![](images/05_typeahead_hld_detail.png)

**Distinctive points**
Collection,Aggregator, Trie builder, WebSocket, localcopy in browser, MapReduce, normalize frequency
AJAX, Trie partitioning, Track the top searches

## 6. Google Docs

**FRs**
Document collaboration, Conflict resolution, Suggestions, View count, History

**Storage**
relational database(previlage), NoSQL(comments), edit history(time series DB), blob storage(image), distributed cache 

![](images/06_google_doc_hld.png)

**Distinctive points**
limited number of readers and writers, Operation Queues
**Commutativity**: The order of applied operations shouldn’t affect the end result.
**Idempotency**: Similar operations that have been repeated should apply only once.
1. **Operational transformation**
**Causality preservation**: If operation a happened before operation b, then operation a is executed before operation b.
**Convergence**: All document replicas at different clients will eventually be identical.

2. A **The Conflict-free Replicated Data Type (CRDT)** satisfies both commutativity and idempotency by assigning two key properties to each 
character:
1. It assigns a globally unique identity to each character.
2. It globally orders each character.

![](images/06_google_doc_crdt.png)
