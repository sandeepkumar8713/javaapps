# Monolith 

Link : https://www.atlassian.com/microservices/microservices-architecture/microservices-vs-monolith

A monolithic architecture is a traditional model of a software program, which is built as a **unified unit** that is 
**self-contained and independent** from other applications.

## Advantages : 
1. **Easy deployment** – One executable file or directory makes deployment easier.
2. **Development** – When an application is built with one code base, it is easier to develop.
3. **Performance** – In a centralized code base and repository, one API can often perform the same function that
   numerous APIs perform with microservices.
3. **Simplified testing** – Since a monolithic application is a single, centralized unit, end-to-end testing can
   be performed faster than with a distributed application. 
4. **Easy debugging** – With all code located in one place, it’s easier to follow a request and find an issue.

## Disadvantages : 
1. **Slower development speed** – A large, monolithic application makes development more complex and slower.
2. **Scalability** – You can’t scale individual components.
3. **Reliability** – If there’s an error in any module, it could affect the entire application’s availability.
4. **Barrier to technology adoption** – Any changes in the framework or language affects the entire application, 
   making changes often expensive and time-consuming.
5. **Lack of flexibility** – A monolith is constrained by the technologies already used in the monolith.
6. **Deployment** – A small change to a monolithic application requires the redeployment of the entire monolith.

# Microservices
A microservices architecture, also simply known as microservices, is an architectural method that relies on a series of 
**independently deployable services**. These services have their **own business logic and database** with a specific goal. 
Updating, testing, deployment, and scaling occur within **each service**. Microservices **decouple major business**,
domain-specific concerns into separate, independent code bases.

## Advantages : 
1. **Agility** – Promote agile ways of working with small teams that deploy frequently.
2. **Flexible scaling** – If a microservice reaches its load capacity, new instances of that service can rapidly be deployed to   
   the accompanying cluster to help relieve pressure. We are now multi-tenanant and stateless with customers spread across 
   multiple instances. Now we can support much larger instance sizes. 
3. **Continuous deployment** – We now have frequent and faster release cycles. Before we would push out updates once a week and 
   now we can do so about two to three times a day. 
4. **Highly maintainable and testable** – Teams can experiment with new features and roll back if something doesn’t work. 
   This makes it easier to update code and accelerates time-to-market for new features. Plus, it is easy to isolate and fix 
   faults and bugs in individual services.
5. **Independently deployable** – Since microservices are individual units they allow for fast and easy independent deployment
   of individual features. 
6. **Technology flexibility** – Microservice architectures allow teams the freedom to select the tools they desire. 
7. **High reliability** – You can deploy changes for a specific service, without the threat of bringing down the entire   
   application.

## Disadvantages :
1. **Development sprawl()** –  Microservices add more complexity compared to a monolith architecture, since there are more 
   services in more places created by multiple teams. If development sprawl(**stretched or spread out**) isn’t properly
   managed, it results in slower development speed and poor operational performance. 
2. **Exponential infrastructure costs** – Each new microservice can have its own cost for test suite, deployment playbooks, 
   hosting infrastructure, monitoring tools, and more.
3. **Added organizational overhead** – Teams need to add another level of communication and collaboration to coordinate
   updates and interfaces. 
4. **Debugging challenges** – Each microservice has its own set of logs, which makes debugging more complicated. Plus, a
   single business process can run across multiple machines, further complicating debugging. 
5. **Lack of standardization** – Without a common platform, there can be a proliferation of languages, logging standards,
  and monitoring. 
6. **Lack of clear ownership** – As more services are introduced, so are the number of teams running those services. Over
   time it becomes difficult to know the available services a team can leverage and who to contact for support.

