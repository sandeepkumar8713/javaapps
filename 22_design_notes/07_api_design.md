# API design

Medium : https://medium.com/startlovingyourself/api-design-for-microservices-a4881036b05e

# Introduction :
1. Api design plays great role in building applications. If you don’t design proper response, consumer services won't
   be knowing whats going on behind the service call.
2. We always write atleast 3 layer for any given API ex. **Controller, service, dao(domain object access)**.

## Endpoints : 
1. Let us take this example : 
    1. getEmployees
    2. addEmployee
    3. deleteEmployee
    4. findEmployeeById
    5. UpdateEmployee etc.
2. If we define API's like above, we would end up designing multiple endpoints for the same resource ex. employee, customer etc. 
3. Api end points should not contains any **verbs**, **actions**. 
4. We should have only **noun**(resource with plural form) in endpoint. 
5. We should be having endpoints like as follows:

    GET path/employees \
    DELETE path/employees/id  \
    POST path/employees \
    PUT path/employees/id 

## Exception Handling : 
1. This is where we need to be very careful as it gives lot of information to consumer about your api when it is not\
    working as expected. We should always try to come up with minimal required status codes, messages while working\
    on writing new api. 
2. Lets assumer we are writing an api which process payment for given customer order:

    VALIDATION_ERROR when given input is not correct ex, few fields might be blank etc. \
    ORDER_NOT_FOUND when don’t find order with given info. \
    ORDER_CANCELLED when order got cancelled. \
    REFUND_FAILED when refund already processed. \
    INVALID_AMOUND invalid amount \
    TECHNICAL_ERROR

3. If we use TECNICAL_ERROR for each case, consumer will have difficulty in understanding the output of our api when\
   something goes wrong. 

4. **Suggestions** : 
    1. Throw **custom exception**(create custom exception which takes **message, status code** as input) from service based \
       on these use cases exp. invalid amount, input validation, refund failed etc, and handle it in controller.
    2. Create **final api response** by passing exception information to response utility method which takes status code, \
       message as input, and give final response.
    3. It is not service's responsibility to return final api response. It is something which should be done at \
       controller layer. **Avoid creating final response at service**. It break principle of ‘“clear separation between layers”.

## Controller :
1. Controller is the place where you get request first, we should always have **dto classes designed for controller**. 
2. Never mix your domain classes with controller as we always expect these two to behave in different manner. 
3. **Suggestions** : 
    1. Domain class(entity/document) can have many fields, but we might not want to send all to consumer. Have \
       **dto(Data transfer object) classes with required fields**.
    2. When you think about comparing two POJO(Plain Old Java Object, object without restriction) objects, you care about \
       all the fields but domain object can be compared by just using ids.
    3. Controller should **not** have any kind of **business logic**.
    4. **Service layer passes status code, message in the form of custom exception** which needs to be handled at controller \
       layer.
    5. Should **not call multiple services** from controller instead have new service which invoke multiple services if required.
    6. Should **not call dao/repository** from controller as it is not controller responsibility to do so.

## Service : 
1. Service is the place where your API **business logic** needs to be written.
**Suggestions** : 
    1. Always try to divide service methods into small methods. Again if we divide it into private methods there will be \
       issues around designing test cases. You can move these **private methods to adapter class, have static methods to achieve** \
       the same as it is difficult to cover all possible use cases of private methods from calling test case. Sometimes it \
       requires to have multiple big configuration to test all the scenarios.
    2. Always do validation on input fields, and throw custom exception with validation error code, and fields specific error \
       message. If there are multiple fields need to be validated, move it to outside of service. Have **validator class** \
       to write validation for all the fields.
    3. Do not **mix two domain object/repo with the same service method**.
    4. When calling **external services**, or other services if we don’t get required data, throw **custom exception** with \
       required status code, error message.
    5. Do’t do anything which is specific to repository layer at service. ex. **building criteria/query at service layer**\
       in case of Mongodb.

## Repository/Dao : 
1. This layer interacts with db in your application. Database scans every record to get required result if we don’t \
   design index around enitity/document which is highly inefficient. **Indexes** help in reducing search space.
2. **Suggestions** : 
    1. We should try understanding **find methods first**, then come up with proper indexing.
    2. Create index only **when it is required** as it might make write, delete operation costly.
    3. If find need to get data based on multiple fields, avoid writing multiple methods instead try building \
       **dynamic query** based on given fields with single repository method. (Hibernate is ORM for java.)

## Unit testing : 
1. Unit testing helps you in testing your code **independently**. if your code methods looks very complex or contains \
   many lines of codes it is always difficult to test it. Try breaking into multiple small methods/
2. It saves lot of time as you don’t need to redeploy your code every time when you make small code changes.
3. It also helps you in **duplicating application** issues, you just need to get configuration around given issue \
   in your test case, and you can easily fix it.

# REST (Representational State Transfer)

Linkedin : https://www.linkedin.com/pulse/api-design-patterns-best-practices-building-robust-apis/


## Common Design patterns :

1. **Resource-Based** : This pattern focuses on organizing API endpoints around resources, which represent entities in \
   the system being exposed via the API.
2. **CRUD** (Create, Read, Update, Delete): This pattern is a common approach for defining the four basic operations \
   (create, read, update, delete) that can be performed on a resource.
3. **HATEOAS** (Hypermedia as the Engine of Application State): This pattern involves including hyperlinks in API responses \
   that allow clients to discover and navigate the API resources.
4. **Filter and Pagination** : This pattern involves implementing filtering and pagination capabilities to allow clients to \
   efficiently retrieve subsets of data from a resource.
5. **Versioning** : This pattern involves providing different versions of an API to support changes in the API without \
   breaking existing clients.


## Best Practices : 
1. **Follow the REST architectural style**: The REST architectural style defines a set of constraints that must be followed to \
   build a RESTful API. These constraints include using HTTP methods correctly, using resource URIs, and using hypermedia to link
   resources. Make sure you follow these constraints to ensure that your API is consistent, reliable, and easy to understand.
2. **Use HTTP methods correctly**: Use HTTP methods like GET, POST, PUT, and DELETE to perform the appropriate action on a resource. \
   For example, use GET to retrieve a resource, POST to create a new resource, PUT to update an existing resource, and DELETE to \
   delete a resource.
3. **Use resource URIs**: Use resource URIs to identify resources in your API. The URI should be unique and consistent, and it should \
   not include any implementation details. For example, instead of using a URI like /API/users/getUserById, use a URI like \
   /API/users/123.
4. **Use hypermedia to link resources**: Use hypermedia to link resources in your API. Hypermedia allows clients to discover \
   related resources and navigate the API without having to know the implementation details. Use hypermedia formats like \
    HAL, JSON-LD, or Siren to provide links to related resources.
5. **Use versioning**: Use versioning to ensure that changes to your API do not break existing clients. Include a version number \
   in the URI or in the HTTP header to indicate which version of the API is being used.
6. **Use authentication and authorization**: Use authentication and authorization to secure your API. Use OAuth 2.0 or a \
   similar protocol to authenticate clients and control access to resources.
7. **Use error handling**: Use error handling to provide informative error messages when errors occur. Use HTTP status codes to \
   indicate the type of error, and include an error message in the response body.

## How to design API

Link : https://www.youtube.com/watch?v=_YlYuNMTCc8

**what, how, result**
number of errors defined (think of expected errors)

Non-ambigous (setting action in the body itself)
No side effect
Atomcity 
Pagination (it breaks stateless property)
Fragment the API (break the response into frames 10kb each with numbers)
If server is loaded, we can reduce the response size. Like sending only the name of users and id, not there details.
