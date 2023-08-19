# Race condition  

Link : https://blog.avenuecode.com/how-to-avoid-race-conditions-in-your-microservice-application

## Introduction : 
1. In 1965, Edsger Dijkstra published a single-page paper in which he described and solved a problem that would impact \
   computer science for the foreseeable future. The premise is simple:
2. Given N computer processes running simultaneously, if two or more of these processes access a resource at the same time, \
   we may incur a consistency problem. The reason is that the resource is shared across the N processes, such that after \
   multiple accesses, we can't determine the resource's state appropriately. This problem is known as a race condition.
3. The solution, as proposed by Dijkstra, was a simple algorithm known as mutual exclusion. It is described as follows:

    while true
        acquire lock
        write resource
        release lock
    end-while
4. In the third line of the snippet above, we determine a code region called critical section, where all execution threads will \
   run synchronously. Essentially, the process that acquires the lock first will execute, while the others will have to wait until \
   it's released.
5. we determine two problems in concurrent programming:
    **Race condition** : two processes accessing the same resource at the same time may lead to inconsistencies;
    **Starvation** : unreleased locks will make the critical section inaccessible by other processes, which will be blocked forever unless someone takes external actions.

Java example : 01_folder/10_producer_consumer.java

## Microservice : 
1. In this context, we need to implement a distributed critical section. This means that the N processes described by Dijkstra \
   now run on different computers! Also, they need to be synchronized by state, meaning that our critical section's lock \
   increased a little in complexity.
2. The pattern we want to propose is similar to Saga design pattern. However, we want to restrict it to the following conditions:
    1. Two transactions with the same state can't happen at the same time (avoid race condition).
    2. The process trying to access a resource will eventually acquire it (avoid starvation).
3. The key to making a distributed critical section is making your application's instances agree that a thread should be blocked \ 
   for a given state. Let's suppose that instances i and j receive requests to update an entity x at the same time. Then, i and j must \
   agree that only one of them will update x at a time. In this article, we'll use an external consistent data repository that i and j \
   can access and agree upon x's state.
4. Consider the enhanced mutual exclusion algorithm below:
    do 
        try lock x
    while x is not locked
    write resource
    release lock x
5. Please notice that the operations in lines 2 and 5 depend on the external data repository, which must be consistent for all of \
   your application's instances. In this case, the critical section in line 4 will now be synchronized for any process of your \
   application.
6. 4 components in below example are : **TransactionService, LockStateService, StateRepository, Lock**

    ```
    public void doInTransaction(T state, Consumer<T> concurrentOperation) {
        try {
            Lock<T> acquiredLock = lockStateService.acquire(state);
            
            doInTransactionWhenLockIsAcquired(acquiredLock, concurrentOperation);
        } catch (Exception exception) {
            /**
             * Any exception during commit or rollback flows? Maybe you'd like
             * to catch them here.
             */
        }
    }
    protected void doInTransactionWhenLockIsAcquired(Lock<T> acquiredLock, Consumer<T> concurrentOperation) {
        try {
            concurrentOperation.accept(acquiredLock.state());
            commit(acquiredLock);
        } catch (Exception exception) {
            rollback(acquiredLock, exception);
        } finally {
            lockStateService.release(acquiredLock);
        }
    }
    ```

## DB :

Link : https://hackernoon.com/how-to-solve-race-conditions-in-a-booking-system

## Problem 
1. Both of these statements update the availability of the room with ID 123 to "FALSE", and insert a booking into the "Booking" \
   table for that room. However, a race condition can occur if these statements are executed concurrently.
```
Alice:
UPDATE Room SET available = FALSE WHERE id = 123;
INSERT INTO Booking (room_id, start_date, end_date) VALUES (123, '2022-01-01', '2022-01-07');
```

```
Bob:
UPDATE Room SET available = FALSE WHERE id = 123;
INSERT INTO Booking (room_id, start_date, end_date) VALUES (123, '2022-01-03', '2022-01-10');
```

2. For example, suppose the initial availability of the room with ID 123 is "TRUE". Alice and Bob concurrently read info from the \
   database saying that the room is available. Both of them reserve this room.
3. The final result (the room being booked for both Alice and Bob) is different from what would be expected if the statements were executed sequentially (either Alice's booking or Bob's booking being rejected).


## Solution 
**Pessimistic concurrency control** : 
1. It is a technique used to prevent race conditions in a database by locking the data that \
   is being accessed or updated. This ensures that only one user can access the data at a time, and other users have to wait \
   until the lock is released before they can access it.
2. A user can execute the following SQL statement. This statement will lock the row with the ID 123 in the "Book" table, 
   and prevent other users from **accessing or updating** that row until the lock is released.
    ```
    SELECT * FROM Room WHERE id = 123 FOR UPDATE;
    ```
3. To release the lock, the **user can commit or roll back** the transaction:
    ```
    COMMIT;  -- releases the lock
    ROLLBACK; - releases the lock and discards any changes made to the data
    ```

**Optimistic concurrency control** :
1. It allows multiple users to access and update the data concurrently, but checks for conflicts before committing the changes.\
   If a conflict is detected, the user is notified and the changes are not applied.
2. Version column can be used to store a "version number" for each booking, which is incremented each time the booking is updated.

    ```
    Alice:
    UPDATE Room SET available = FALSE, version = version + 1 WHERE id = 123 AND version = 1;
    INSERT INTO Booking (room_id, start_date, end_date, version) VALUES (123, '2022-01-01', '2022-01-07', 1);
    ```

    ```
    Bob:
    UPDATE Room SET available = FALSE, version = version + 1 WHERE id = 123 AND version = 1;
    INSERT INTO Booking (room_id, start_date, end_date, version) VALUES (123, '2022-01-03', '2022-01-10', 1);
    ```
3. If both of these statements are executed concurrently, the first UPDATE statement to be executed will increment the "version" \
   of the room with ID 123 to 2, and the second UPDATE statement will fail, as the "version" in the WHERE clause is 1 (so \
   zero rows will be updated with the second transaction).
4. Alternative to above solution
    ```
    UPDATE Room SET available = FALSE WHERE id = 123 AND available = TRUE;
    ```
