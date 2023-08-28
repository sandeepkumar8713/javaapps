# SQL vs NoSQL

**REASONS TO USE A SQL DATABASE**
a. You need to ensure ACID compliancy (Atomicity, Consistency, Isolation, Durability).
b. Your data is structured and unchanging.

**REASONS TO USE A NOSQL DATABASE**
a. Storing large volumes of data that often have little to no structure.
b. Making the most of cloud computing and storage.
c. Rapid development

**NoSQL**
Advantages 4
1. Non-Relational means table-less
2. Mostly Open Source and Low-Cost
3. Easier scalability
4. No need to develop a detailed database model

Disadvantages 3
1. Community not as well defined
2. Lack of reporting tools
3. Lack of standardization

Link : https://www.softwaretestinghelp.com/sql-vs-nosql/

## SQL
**Structured Query Language**, commonly abbreviated as SQL, is a domain-specific programming language that is used for 
**storing, manipulating and retrieving** data in RDBMS (Relational Database Management System).

It is mainly used for managing **structured data** where we have a **relationship** between various entities and variables
of the data.

These type of statements are further classified as shown below:
1. **DDL (Data Definition Language)**: These are the Queries that are used for schema creation and modification. The common DDL 
   commands in SQL include CREATE, ALTER, and DROP.
2. **DML (Data manipulation language)**: This Query is used for performing select, insert, update and delete operations in the    
   database. The common DML commands in SQL are SELECT, INSERT, UPDATE and DELETE.
3. **DCL (Data Control Language)**: Such Queries are used to control access and provide authorization onto the database. The 
   common DCL commands in SQL are GRANT and REVOKE.
4. **TCL (Transaction Control Language)**: These queries are used for controlling and managing transactions to maintain data 
   integrity. The common TCL commands in SQL include BEGIN, COMMIT, and ROLLBACK.

## NoSQL

1. NoSQL (also refers to Not only SQL, non-SQL or non-relational) is a database which gives you a way to manage the data whic
   is in a **non-relational** form i.e. which is not structured in a tabular manner and does **not possess tabular relationships**.
2. NoSQL is increasingly gaining popularity as it is being employed in **big data and real-time applications**. 
3. It is mainly helpful for working with **huge sets of distributed data**. 
4. NoSQL databases are **scalable, high performant and flexible** in nature.

Basically, there are four types of NoSQL databases.

1. **Column**: Wide column stores and arranges the data tables as columns rather than as rows.
   They can query a large volume of data very quickly than the traditional databases. They can be employed for recommendation 
   engines, catalogs, fraud detection, etc.
   Examples: **Cassandra**, HBase, Google BigTable, Scylla, Vertica, etc.

2. **Document**: Document databases, aka document stores and keeps the **semi-structured data** along with its description
   in the document format. Each document has a **unique key** through which it is addressed. They are helpful for content management and mobile application data handling. They are widely used along with **JSON and JavaScript**. Document databases also offer an **API and query language** through which the documents can be fetched based on their contents.
   Examples: Apache, **MongoDB**, MarkLogic, CouchDB, BaseX, IBM Domino, etc.

3. **Key-value**: Key-value databases have their data model based on an associative array (**map or a dictionary**) in which
  the data has represented a collection of **key-value pairs**. They are highly suitable for **session management** and 
  **caching** in web applications.
  Examples: Aerospike, Berkeley DB, Apache ignites, Dynamo, **Redis**, Riak, ZooKeeper, etc.

4. **Graph**: In graph stores, data is organized as **nodes and edges**. You can think of a **node as a record** and 
   **edge as a relationship** between the records in the relational database. This model supports a richer representation
   of data relationships. They are useful for customer relationship Management systems **(CRM), road maps, reservation systems** 
   etc.
   Examples: AllegroGraph, InfiniteGraph, MarkLogic, Neo4j, **IBM graph**, Titan, etc.


## SQL vs NoSQL Differences
1. They have a well-designed pre-defined schema for **structured data**.	
   They have the **dynamic schema for unstructured data**. Data can be flexibly stored without having a pre-defined structure.

2. SQL databases favors **normalized schema**.	
   NoSQL databases favors **de-normalized schema**.

3. **Costly** to scale.	
   **Cheaper to scale** when compared to relational databases.

4. SQL databases are **vertically scalable**. They can be scaled by increasing the hardware capacity (CPU, RAM, SSD, etc.) on a 
   single   server.	
   NoSQL databases are **horizontally scalable**. They can be scaled by adding more servers to the infrastructure to manage large load 
   and lessen the heap.

5. They are a **good fit for complex queries** as SQL has a standard interface for handling queries. The syntax of SQL queries is 
   fixed. 	
   **Not a good fit** for complex queries as there is no standard interface in NoSQL for handling queries. The queries in 
   NoSQL are **not as powerful** as SQL queries.

6. SQL databases properly follow **ACID** properties (Atomicity, Consistency, Isolation & Durability).	
   NoSQL databases properly follow Brewers **CAP theorem** (Consistency, Availability, and Partition tolerance).

7. Adding new data in SQL database **requires some changes** to be made like backfilling data, altering schemas.	
   New data can be **easily inserted** in NoSQL databases as it does not require any prior steps.

8. **Not suitable for hierarchical data** storage.	
   **Suitable** for hierarchical data storage and storing large data sets (E.g. Big Data).
