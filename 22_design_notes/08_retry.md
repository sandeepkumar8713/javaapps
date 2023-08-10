## Retry 


## Retry for API response 

You might want to consider introducing a wait between retries as a lot of transient problem might take more than a few \
seconds to clear. In addition, I would recommend a geometrical increase in wait time to give enough time for system to recover:

```
import time

cnt=0
max_retry=3
while cnt < max_retry:
    try:
        response = requests.get(uri)
        if response.status_code == requests.codes.ok:
            return json.loads(response.text)
        else:
            # Raise a custom exception
    except requests.exceptions.RequestException as e:
        time.sleep(2**cnt)
        cnt += 1
        if cnt >= max_retry:
            raise e
```

## Retry of message from the queue 

Link : https://tech.groww.in/implementing-retry-mechanism-using-delayed-queues-and-dead-letter-queues-in-kafka-ba4e74c212bd 

**Delayed Queue** : 
1. If a message fails to process after being consumed, we may want to process it again for a known repeated number of times. \
Delayed queues can help us with that. The idea is to add the message back into the queue to be processed, after a certain delay \
to allow the application:
    1. Some time to recover
    2. Wait for other processes to finish
    3. Consume other messages
2. Let's create a **new queue** with a topic named delayed_queue. A listener will listen to messages on this topic. To add the \
   delay, all we need to do is use Thread.sleep(<time in ms>). This makes the thread, which runs the Kafka listener, to sleep \
   and stop consuming any more messages till the time passes. The message can then be **pushed back into the main processing queue**. \
   So we add an additional field in our message class which will hold the delayedTime = currentTime + timeToDelay, before the \
   message is pushed into the delayed queue. 

**Multiple Retries** : 
1. Power of retry mechanism actually lies in allowing the processing of messages multiple times. To implement this we need to add \
   **retryCount** field in our class which will store the number of times the message needs to get processed. 
2. Each time it is put into the delayed queue, we reduce the retryCount with the special case of 0, where we stop further  \  
   processing and put the message in a special queue called **dead-letter-queue**.

**Dead Letter Queue** : 
1. A dead letter queue is like a **graveyard of messages**. All the messages that couldn’t be processed will be consumed by a \
   dead letter queue. The advantage of having this queue is that it allows the developer to get the messages that could not be \
   delivered and take fallback actions appropriately.

2. Implementation of a dead letter queue is pretty straight forward. Just having another Kafka consumer to a new topic where \
   all dead messages will be sent. But the key concept is to understand when to push messages to the dead letter queue. \
   The following cases will help us understand:
    1. **Runtime Exceptions**: Catch the exception and push the message into the dead letter queue to understand why the \
       exception occured.
    2. **Retry Count Exhausted**: If retryCount reaches 0, then manual processing of the message may be required by the operations team.
    3. **Large processing time**: If a message takes a very long time to be processed, incurring delays to other messages.
