# REST (Representational state transfer)

## what actually process happens in background when we access website from browser
http://edusagar.com/articles/view/70/What-happens-when-you-type-a-URL-in-browser

**Step 1.** URL is typed in the browser.

**Step 2.** If requested object is in browser cache and is fresh, move on to Step 8.

**Step 3.** DNS lookup to find the ip address of the server
Check browser cache, Check OS cache, Router Cache, ISP cache

**Step 4.** Browser initiates a TCP connection with the server.

**Step 5.** Browser sends a (get/post) HTTP request to the server.
header content
**User-Agent** header specifies the browser properties,
**Accept-Encoding** headers specify the type of responses it will accept.
**Connection header** tells the server to keep open the TCP connection established here.
The request also contains **Cookies**,

**Step 6.** Server handles the incoming request
HTTP request made from browsers are handled by a special software running on server - commonly known as **web servers** e.g. 
**Apache, IIS, WSGI, Uvicorn** etc. 
**Web server** passes on the request to the proper **request handler** - a program written to handle web services e.g. PHP, ASP.
NET, Ruby, Servlets

**Step 7.** Browser receives the HTTP response
Following is a very brief summary of what a status code denotes:
1xx indicates an **informational message** only
2xx indicates **success** of some kind
3xx **redirects** the client to another URL
4xx indicates an error on the **client's part**
5xx indicates an error on the **server's part**
**Content-Type** tells the type of the content the browser has to show,
**Content-length** tells the number of bytes of the response.
Using the Content-Encoding header’s value, browsers can decode the blob data present at the end of the response.

**Step 8** Browsers displays the html content
**Rendering** of html content is also done in **phases**.
If the html response contains an image in the form of img tags such as <img src="/assets/img/logo.png"/>, browser will send a
HTTP GET request to the server to **fetch the image** following the complete set of steps which we have seen till now.

**Step 9.** Client interaction with server
Once a html page is loaded, there are several ways a user can **interact** with the server.

**Step 10.** AJAX queries
Another form of client interaction with server is through AJAX(**Asynchronous JavaScript And XML**) requests.
This is an asynchronous GET/POST request to which server can send a response back in a variety of ways - json,
xml, html etc. AJAX requests doesn't hinder the current view of the webpage and **work in the background**. Because of this, one can **dynamically modify** the 
content of a webpage by calling an AJAX request and updating the web elements using Javascript.

## REST Introduction : 
1. (REST) is a software architecture that imposes conditions on how an **API** should work. REST was initially created as a
   **guideline** to manage communication on a complex network like the internet. 
2. Uses **HTTP** methods to interact with resources
2. Supports **caching and scalability**.
3. Works well for **CRUD (Create, Read, Update, Delete)** operations
4. Allows for **stateless** communication between client and server
5. Can be used with a variety of **programming languages and frameworks**.

## Constraints : 
1. **Client-server architecture**: This constraint requires that the client and server be **separated** from each other, with each \
   **responsible for a specific set** of functionalities. This separation allows for greater **scalability and flexibility in the \ 
   system, as the client and server can be updated or replaced independently of each other.

2. **Statelessness**: A RESTful API should be stateless, meaning that each request contains all the necessary information for the \
   server to understand and respond to it. The server does not maintain any session state between requests, which makes the system \
   more scalable and easier to manage.

3. **Cacheability**: REST APIs should be designed to take advantage of caching, which can improve performance by reducing the \ 
   number of requests sent to the server. Responses should include information that allows clients to determine whether \
   the response can be cached, and for how long.

4. **Layered system**: A RESTful API should be designed as a layered system, where each layer provides a **specific set of functionalities**.  
   This allows for greater flexibility in the system, as layers can be added, removed, or modified without affecting the rest of the system.

5. **Uniform interface**: The uniform interface constraint defines the standard interface that all components of the API must \
   adhere to. This includes the use of standard **HTTP methods (GET, POST, PUT, DELETE)** for CRUD operations, as well as a \
   consistent data format (usually JSON or XML) for requests and responses.

6. **Code on demand**: This is an optional constraint that allows for executable code to be transferred from the server to the \
   client, which can add additional functionality and flexibility to the API. However, this constraint is not commonly used in \
   RESTful APIs and should be used with caution, as it can add complexity to the system.

https://nordicapis.com/web-service-what-is-a-rest-api-and-how-does-it-work/

An API (**Application Programming Interface**) is a set of guidelines that define how devices and applications communicate with each other.

## What Is a REST API?
REST refers to a set of **specific rules that dictate how web service applications communicate over the internet**. Therefore, a 
**RESTful API** is an API that follows these rules, providing **flexibility, bandwidth, and speed advantages** to software integrations and communications.

REST APIs serve as the **middleman** between your client and your host server, fielding resource requests and delivering 
responses via **HTTP**. In a REST design, the **client initiates** the interaction — for example, accessing a webpage via
a URL is a commonly used HTTP request.

**REST has 2 components** : Client and Server

## Client Request

A client is a program or person who accesses the services of the API. For example, your program would be the client if you 
were integrating with a web application like Instagram or Youtube. Your browser would be the client if you were requesting a URL.

The most common HTTP methods are:
    **GET**: To retrieve a resource.
    **POST**: To create a new resource.
    **PUT**: To edit or update an existing resource.
    **DELETE**: To delete a resource.

## Server Response

A **resource** refers to the **specific information** an API provides to a client. This could be anything from hashtags to 
profiles, comments, web addresses, tweets, etc. All resources have a unique name called a **resource identifier**, and they’re 
all stored on a server.

When a client makes a request using a RESTful API, the server transfers a **standardized representation** of the resource’s
state to the client’s system. This means the server does not send the client the actual resource but a 
**representation of its state** – i.e., the state of the resource at a **particular timestamp**.

Responses are sent back in a **lightweight format** to aid interpretability. The **JSON** (JavaScript Object Notation) format is 
popular because it’s readable by both humans and machines and excels in its strides toward promoting web accessibility. It’s also 
**compatible** with many other programming languages.

Some alternative API data formats include **XML, YAML, CSV, HTML, and plain text**.

## SOAP (Simple Object Access Protocol)
SOAP is a prescribed XML-based **messaging protocol with strict standards and syntax**. REST is a set of **design principles** 
that dictate specific guidelines that developers need to follow but, in many areas, grants copious flexibility.

| No. | SOAP                                            | REST                                                                                         |
|-----|-------------------------------------------------|----------------------------------------------------------------------------------------------|
| 1   | protocol for communication between applications | REST is an architecture style for designing communication interfaces.                        |
| 2   | API exposes the operation                       | API exposes the data.                                                                        |
| 3   | Independent and can work with any transport protocol. | works only with HTTPS.                                                                 |
| 4   | Supports only XML data exchange.                | supports XML, JSON, plain text, HTML                                                         |
| 5   | messages are larger, which makes communication slower | faster performance due to smaller messages and caching support                         |
| 6   | difficult to scale. The server maintains state by storing all previous messages exchanged with a client. | is easy to scale. It’s stateless, so every message is processed independently of previous messages. |
| 7   | supports encryption with additional overheads.  | supports encryption without affecting performance.                                           |
| 8   | useful in legacy applications and private APIs. | is useful in modern applications and public APIs.                                            |
