# DoorDash

Medium : https://medium.com/partha-pratim-sanyal/system-design-doordash-a-prepared-food-delivery-service-bf44093388e2

## Points to be Considered : 
    Data model, Scalabilty, Redundancy, Fault tolerance

## Main actors :
    Customers/consumers, Restaurant (Merchant), Doordashers (Drivers). DoorDash Admin (from the company)

## Features :
Customers should be able to:
1. Search for restaurants based on the cuisines type, menu items, etc.
2. Create a cart, add menu items to the cart and order.
3. Receive notifications about the status of an order once placed.
4. Track the status of the order on the app.
5. Cancel the order.
6. Pay for the order.
7. Create/Update their account and contact information.

Restaurants should be able to:
1. Create their profile (onboarding) and create/refresh/add new menu items, pictures.
2. Receive notifications about orders placed, assigned doordasher, and update the status of order etc.
3. Offboarding: If the restaurant goes out of business, or decides to discontinue taking online orders.

Doordashers should be able to:
1. Receive notifications about the available orders in the area, from which they can choose.
2. Know when the order is available for pickup.
3. Inform the customer/restaurant of any problems they encounter while executing the order pickup/delivery.
4. De-register in case they don’t want to continue with the job anymore.

## Non Functional Requirements :
1. Latency : The search functionality should be very fast. The ordering experience should also not have high latency and \
must be seamless and fast.
2. Consistency: When a new restaurant is onboarded, or a new menu is added, the information needn’t be available immediately.\
**Eventual consistency** is desirable. However, when an order is placed, the customer, the restaurant, and the door dasher should\
see the same order without any issues. 
3. Availability : High availability is desirable for the best experience of a customer and also the restaurants that are \
processing the order, as well as the doordasher.
4. High Throughput : The system should be able to handle high peak load without problems or failures.

## Capacity :  
On average, each area code can have 100 restaurants. Each restaurant can have 15 dishes that can be served. \
Total records of dishes = 10M * 100 * 15 = 15B. Number of customers: 20M \
If each customer on an average places 2 orders every day, number of orders in a day = 40M \
In general, the searching of menus/restaurants will be **read-heavy** and the ordering functionality will be **write-heavy**.

## Entities/Classses : 

    Person, Address, Discount code, Payment, Order, Order Item, Menu, Menu Item

## Data Storage :
1. The choice of the database usually depends on the amount of data that is being stored, the ease of scaling, partitioning \
   replication, and several other factors. 
2. As evident from the capacity estimation, the amount of data of restaurants, menu descriptions, user data, dasher data, \
   etc is going to be huge, and hence, **NoSQL/ Columnar database** like Cassandra could be used. The structure of data, \
   especially attributes might also **vary** between restaurants and it could be difficult to fit in the data into a relational schema.
3. Pictures (Restaurants, menu items) can be stored in an object storage service like **Amazon S3**.
4. **Ordering** is a transactional process and can be stored in **Oracle/MySQL/Postgres**.

## Components :  
1. **Search** : 
    1. Searching on menu items, cuisines, restaurants, among other things. This particular part of the entire system will \
       be read-heavy. 
    2. We can think of leveraging popular off-the-shelf search offerings from the market such as Elasticsearch or Apache \
       Solr for quick lookup as per user search parameters. 
    3. We will need to have a queue in place to process asynchronous updates to the search cluster. 
    4. When the **Restaurant Profile Service** creates/updates a restaurant/menu data by performing CRUD operations on the database, \
       it can also post an event to the queue. This event could be any of the CRUD. 
    5. We need a data indexer that listens to the queue for such events. The data indexer then picks up the event, \
       runs a query against the database to formulate a document as per the correct format, and posts the data into \
       the search cluster. 
    6. We also need to have a **Restaurant Search Service** that execute queries on the search cluster based on user \
       inputs and returns the result to be displayed on the user interface.
    7. **Elasticsearch** has a Geo-distance query, which can be leveraged to return all the restaurant/menus that the user is \
       searching for based on a defined radius from the location of the user. 
    8. We should use a cache, working alongside the Restaurant Search Service.

2. **Ordering** :
    1. Ordering Service : This service will manage the menu selection, shopping cart management, and placement of food orders.\
    It will process the payment using an External Payment Gateway and persist the result into an Orders database.

3. **Order Fulfillment Service** :
    1. The restaurant accepts the order
    2. Notifies the customer of any delay or any change in the order, if necessary (Using Notification Service).
    3. Customers can check the status of their orders.
    4. Doordashers can check if the order is ready to be picked up. They can also view the details of the current 
       order they are picking up/delivering.
    5. Notifies the doordasher when an order is ready to be picked up.

4. **User Profile Management & Preferences Service** : 
    1. The actors in the system, namely, customers, restaurant staff, doordashers, and system admin will need a way to create \
        their profile with personal information, contact, address, and will be assigned a role based on their profile. 

5. **Doordasher Dispatch Service** : A doordasher will be able to:
    1. View pick-up orders from a list, that they can accept.
    2. Accept an order.
    3. View past orders that they have accepted.
    4. View the customer information from the order in case they need to communicate with them about delivering, or inform \
       the restaurant about any problem that stops them from picking up or delivering the food.
    
6. **Restaurant Profile Service** : A restaurant or business can:
    1. Onboard
    2. Update/Delete their profile.
    3. Create/Update/See/Delete menus, dishes etc.
    4. Upload images of their restaurants or dishes/menus into an object storage service.
    5. View their financial details based on past orders etc.
    6. Setup a payment method for money transfers.

7. **Notification Service** :  This service is responsible for delivering notifications to every actor with the system.

## Flow :
1. Customer will search the item and place order. The ordering service will the order in DB and publish the message(#1) in queue. \
   Notification Service will notify the resturant. Once the order is accepted by the restaurant (using the Order fullfilling \
   service), OFS will publish a MSG(#2) to the queue regarding the acceptance of the order.
2. Doordasher Dispatch Servic will read the MSG (#2) and post an area-specific MSG(#3) to the queue. Notifiaction service will read \
   MSG(#2) and notify the customer that the order was accepted by the restaurant. NS will read MSG(#3) and notify the doordashers \
   in the area about the availability of the order for their acceptance to process.
3. The doordasher will accept the order using the DDS. DDS will then post a MSG(#4) to the queue about doordasher \
   assignment/acceptance. NS will read MSG(#4) and notify the customer and the restaurant about the doordasher assignment. \
   Once the food is ready to be picked up, the restaurant will update the status of the order using OFS. OFS will post \
   a MSG(#5) to the queue that the food is ready to be picked up. NS will read MSG(#5) and notify the assigned doordasher \
   as well as the customer that the food is ready to be picked up.
4. After the doordasher picks up the food, Restaurant staff will update the status of the order using OFS. OFS will then \
   post a MSG(#6) to the queue that the order has been picked up. NS reads MSG(#6) and notifies the customer that the order \
   is picked up.
5. Doordasher reaches the customer and delivers the food and marks the order as complete!. DDS posts a MSG(#7) to the queue \
   about delivery completion. OFS reads MSG(#7) and updates the status of the order to completed. NS reads MSG(#7) and \
   notifies the restaurant and the customer about the delivery of food.


## System APIs : 
Ordering food:
    
    search (array of search terms), addToCart(menu-item), order(cart), status(orderId), retrieveOrder(orderId), cancelOrder(orderId)

Profile management:
    
    createProfile, updateProfile, updateAddress, updatePaymentMethod

## Data Partitioning : 
Restaurant data can be partitioned based on:
    Area code, RestaurantId, Menu items/cuisines/dishes, Combination of area code & restaurantId

## Replication & Fault Tolerance :
1. Each service should be horizontally scalable. 
2. The NoSQL infrastructure will also have multiple nodes. The search system can have multiple nodes set up. 
3. Queues can have partitions and replication as well.
4. Autoscaling could be enabled to handle loads on individual components, for example, spinning up more instances \
   in the face of a high peak load. 
5. In case any of the nodes go down, or any partition in the queueing infrastructure goes down, another instance \
   can take up the job. The crashed node can then do a cleanup and restart using a process called **self-healing**.


## Caching :
1. Based on the recent orders in an area, or the most ordered items, or searched items, data could be cached so that \
   the Restaurant Search Service could look up such information from a distributed cache,
2. Images of restaurants and dishes could be cached as well, instead of hitting the object storage all the time. The \
   cache will hold the popular or most-ordered menu items/restaurants in a particular area and the search screen should \
   show those options by default.
3. Redis, hazelcast, and Memcached. **Least Recently Used (LRU) or Least Frequently Used (LFU)** algorithm, or a combination \
   of both can be our cache eviction strategy for our use case. 

## Security : 
1. HTTPS/SSL -TLS for securing the wire. All communications between the web or mobile clients need to be on TLS.
2. OAuth 2.0 for token authorization/refresh/generation.
3. In case the queuing system is Kafka — SASL per topic/SSL

## Load Balancing :
1. Here are several load balancing techniques that could be used like **Least Connection Method, Round Robin method**, etc.
2. For example, in Kafka consumer consumption divides partitions over consumer instances within a consumer group. Each \
   consumer in the consumer group is an exclusive consumer of a fair share of partitions. This is how Kafka does load \
   balancing of consumers in a consumer group.
