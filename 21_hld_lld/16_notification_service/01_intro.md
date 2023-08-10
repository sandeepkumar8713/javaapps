# Notification Service

Link : https://www.notificationapi.com/blog/notification-service-design-with-architectural-diagrams
Another Link : https://www.linkedin.com/pulse/notification-system-design-rajiv-srivastava/

Requirements:
1. **Send API**: Expose an authenticated end-point so we can trigger sending notifications from any back-end and microservice
2. **Supported Channels**: Support sending notifications to any channel that exposes an API, e.g., Email, SMS, Push
3. **User Preferences**: Allow users to pick their user preferences on each notification and channel
4. **Respecting downstream service limits**: Avoid getting throttled or suspended by your email or SMS service
5. **Scalable**: Allow horizontal scaling for (theoretically) unlimited scaling

## A Quick Overview
Let's imagine that your code should send a notification. The numbers below correspond with the numbers you see on the diagram.
1. Your code calls the POST /send endpoint. The request contains the userId of the recipient, the type of the notification, and the contents of the notification for every supported channel.
2. The `/send` end-point authenticates the request using OAuth2's Client Credentials Flow.
3. It then requests the user's notification preferences from the database. The preferences indicate whether the user is subscribed to a particular notification and channel or not.
4. It will read the user attributes such as email address or phone number from the database.
5. This end-point will form a message object containing user attributes from step (4) along with the channels and content for each 
   channel. However, it will exclude disabled channels based on step (3). 
6. Finally, the message is sent to a fan out service.
7. The fanout service is configured to broadcast incoming messages to job queues. However, there is filtering in place to ignore   
   job queues related to channels that are not listed inside the message.
8. There is a job queue and processor per channel. The processor picks up the job and requests the appropriate service, e.g., a   
   transactional email or SMS service.

## Important Architecture Decisions:
1. **POST /sent**
    1. You notice that the request to this end-point only contains the userId and not the email or phone number. This allows the services that send notifications have no knowledge of your users.
    2. The end-point is behind a load balancer to ensure scalability.
    3. The end-point is not protected with your regular user-facing authentication. Since the service that makes the request is a "program" itself, you need to use a different authentication mechanism known as the OAuth2 Client Credential Flow used for server-to-server communication. Here are links to how to do this in Auth0 and Cognito.

2. **User Preferences**
    1. Use a highly scalable NoSQL or key/value pair database. Structure the records as: KEY: sample_user_id:sample_notification_id, VALUE: [{channel: "email", state: true}, {channel: "sms", state: false}]‍
    2. When the send end-point sees "false" values in the records, it will remove the related channel from the message sent to the fanout. If a record for a channel does not exist, it means the user has not explicitly set their preferences. In this case, you need to agree to a default.
    3. The information in the user_preferences table is updated by the user through your UI, through a normal end-point protected by your common authentication mechanisms.

3. **Fan Out**
    1. Fanout takes a message and duplicates it to various places. They are cheap and highly scalable. In AWS, use SNS. In Google Cloud Platform, use Pub/Sub, and in Azure, use topics and subscriptions.
    2. You can configure filtering between the fanout and job queues to avoid sending unnecessary messages to job queues of channels that have been excluded. For example, in AWS SNS, you can specify that the email job queue should only receive the fanout message if the message contains the "email" property inside the "channels" property.


4. **Job Processing**
    1. Queues hold on to messages until your job processors process them. They are also cheap and highly scalable. Job processors are code that takes messages from the job queues and processes them. They can scale based on the number of messages in the queue.
    2. In our case, the job processor should make an API call to the appropriate service to send out the notification through a transactional email service.
    3. Most email, SMS, or similar delivery services have strict guidelines on the amount and quality of messages you send. You should also carefully review these and put proper systems in place. Here is our guide on how to prevent getting suspended on AWS SES.
    4. You can configure a max number of job processors to avoid hitting the rate limits of the delivery services.

## Further Improvements
Here are a few things that are possible but we haven't covered. If you need any of these capabilities, read the next section.

1. The architecture of a scalable in-app notification service - they require their own APIs, tables, etc., so they deserve their own article.
2. Removing notification contents from the code and instead allowing your product and design team to edit the notifications visually without code change.
3. Dashboard for your team to enable/disable notifications or specific channels without code changes.
4. Collecting and displaying open/click report.

## APIs
Userid is saved in DB. For this user email id and phone are already saved.

**POST /send**

    {
        userId : '123',
        notificationTypeId : 'alert',
        channels : {
            email : {
                subject : "",
                body : "",
            },
            sms : {
                body : "",
            }
        }
    }


**PUT /userPrefrence**

    {
        userId : '123',
        preferences : [
            {
                notificationTypeId : "alert",
                channel : "email",
                enabled : True
            },
            {
                notificationTypeId : "alert",
                channel : "sms",
                enabled : False
            },
        ]
    }

