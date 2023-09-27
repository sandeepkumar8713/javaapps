## Teradata 

Link : https://www.tutorialspoint.com/teradata/teradata_introduction.htm

Following are some of the **features** of Teradata:

    Unlimited Parallelism − Teradata database system is based on Massively Parallel Processing (MPP) Architecture. MPP architecture divides the workload evenly across the entire system. Teradata system splits the task among its processes and runs them in parallel to ensure that the task is completed quickly.

    Shared Nothing Architecture − Teradata’s architecture is called as Shared Nothing Architecture. Teradata Nodes, its Access Module Processors (AMPs) and the disks associated with AMPs work independently. They are not shared with others.

    Linear Scalability − Teradata systems are highly scalable. They can scale up to 2048 Nodes. For example, you can double the capacity of the system by doubling the number of AMPs.

    Connectivity − Teradata can connect to Channel-attached systems such as Mainframe or Network-attached systems.

    Mature Optimizer − Teradata optimizer is one of the matured optimizer in the market. It has been designed to be parallel since its beginning. It has been refined for each release.

    SQL − Teradata supports industry standard SQL to interact with the data stored in tables. In addition to this, it provides its own extension.

    Robust Utilities − Teradata provides robust utilities to import/export data from/to Teradata system such as FastLoad, MultiLoad, FastExport and TPT.

    Automatic Distribution − Teradata automatically distributes the data evenly to the disks without any manual intervention.


The key components of Teradata are as follows:

    Node − It is the basic unit in Teradata System. Each individual server in a Teradata system is referred as a Node. A node consists of its own operating system, CPU, memory, own copy of Teradata RDBMS software and disk space. A cabinet consists of one or more Nodes.

    Parsing Engine − Parsing Engine is responsible for receiving queries from the client and preparing an efficient execution plan. The responsibilities of parsing engine are −

        Receive the SQL query from the client

        Parse the SQL query check for syntax errors

        Check if the user has required privilege against the objects used in the SQL query

        Check if the objects used in the SQL actually exists

        Prepare the execution plan to execute the SQL query and pass it to BYNET

        Receives the results from the AMPs and send to the client

    Message Passing Layer − Message Passing Layer called as BYNET, is the networking layer in Teradata system. It allows the communication between PE and AMP and also between the nodes. It receives the execution plan from Parsing Engine and sends to AMP. Similarly, it receives the results from the AMPs and sends to Parsing Engine.

    Access Module Processor (AMP) − AMPs, called as Virtual Processors (vprocs) are the one that actually stores and retrieves the data. AMPs receive the data and execution plan from Parsing Engine, performs any data type conversion, aggregation, filter, sorting and stores the data in the disks associated with them. Records from the tables are evenly distributed among the AMPs in the system. Each AMP is associated with a set of disks on which data is stored. Only that AMP can read/write data from the disks.


Storage Architecture

When the client runs queries to insert records, Parsing engine sends the records to BYNET. BYNET retrieves the records and sends the row to the target AMP. AMP stores these records on its disks. Following diagram shows the storage architecture of Teradata.

Retrieval Architecture

When the client runs queries to retrieve records, the Parsing engine sends a request to BYNET. BYNET sends the retrieval request to appropriate AMPs. Then AMPs search their disks in parallel and identify the required records and sends to BYNET. BYNET then sends the records to Parsing Engine which in turn will send to the client. Following is the retrieval architecture of Teradata.

Link : https://www.tutorialspoint.com/teradata/teradata_table_types.htm

**Derived Table** : Derived tables are created, used and dropped within a query. These are used to store intermediate results
within a query.

**Volatile Table** : Volatile tables are created, used and dropped within a user session. Their definition is not stored in data 
dictionary. They hold intermediate data of the query which is frequently used. 

**Global Temporary Table** : The definition of Global Temporary table is stored in data dictionary and they can be used by many 
users/sessions. But the data loaded into global temporary table is retained only during the session. You can materialize up to 
2000 global temporary tables per session. 

**Permanent Space** : Permanent space is the maximum amount of space available for the user/database to hold data rows. Permanent tables, journals, fallback tables and secondary index sub-tables use permanent space.

Permanent space is not pre-allocated for the database/user. They are just defined as the maximum amount of space the database/user can use. The amount of permanent space is divided by the number of AMPs. Whenever per AMP limit exceeds, an error message is generated.
Spool Space

**Spool space** is the unused permanent space which is used by the system to keep the **intermediate results** of the SQL query. Users without spool space cannot execute any query.
Similar to Permanent space, spool space defines the maximum amount of space the user can use. Spool space is divided by the number of AMPs. Whenever per AMP limit exceeds, the user will get a spool space error.
Temp Space

**Temp space** is the unused permanent space which is used by Global Temporary tables. Temp space is also divided by the number of AMPs.

Secondary index is not involved in data distribution.
Secondary index values are stored in sub tables. These tables are built in all AMPs.
Secondary indexes are optional.
They can be created during table creation or after a table is created.
They occupy additional space since they build sub-table and they also require maintenance since the sub-tables need to be 
updated for each new row.

There are two types of secondary indexes −
Unique Secondary Index (USI)
Non-Unique Secondary Index (NUSI)

Environment Information
1. Number of Nodes, AMPs and CPUs
2. Amount of memory

Data Demographics
1. Number of rows
2. Row size
3. Range of values in the table
4. Number of rows per value
5. Number of Nulls

There are three approaches to collect statistics on the table.
1. Random AMP Sampling
2. Full statistics collection
3. Using SAMPLE option

compression Limitations
    Only 255 values can be compressed per column.
    Primary Index column cannot be compressed.
    Volatile tables cannot be compressed.

**EXPLAIN** command returns the execution plan of parsing engine in English. It can be used with any SQL statement except on 
another EXPLAIN command. When a query is preceded with EXPLAIN command, the execution plan of the Parsing Engine is returned to 
the user instead of AMPs.

**Hashing logic**
1. The client submits a query.
2. The parser receives the query and passes the PI value of the record to the hashing algorithm.
3. The hashing algorithm hashes the primary index value and returns a 32 bit number, called Row Hash.
4. The higher order bits of the row hash (first 16 bits) is used to identify the hash map entry. The hash map contains one AMP #. Hash map is an array of buckets which contains specific AMP #.
5. BYNET sends the data to the identified AMP.
6. AMP uses the 32 bit Row hash to locate the row within its disk.
7. If there is any record with same row hash, then it increments the uniqueness ID which is a 32 bit number. For new row hash, uniqueness ID is assigned as 1 and incremented whenever a record with same row hash is inserted.
8. The combination of Row hash and Uniqueness ID is called as Row ID.
9. Row ID prefixes each record in the disk.
10. Each table row in the AMP is logically sorted by their Row IDs.

**Single Table Join Index** : Single Table Join index allows to partition a large table based on the different primary index columns than the one from the base table.

**Multi Table Join Index** : A multi-table join index is created by joining more than one table. Multi-table join index can be used to store the result set of frequently joined tables to improve the performance.

**Aggregate Join Index** : If a table is consistently aggregated on certain columns, then aggregate join index can be defined on the table to improve the performance. One limitation of aggregate join index is that it supports only SUM and COUNT functions.

A **stored procedure** contains a set of SQL statements and procedural statements. They may contain only procedural statements. The definition of stored procedure is stored in database and the parameters are stored in data dictionary tables.
Advantages
1. Stored procedures reduce the network load between the client and the server.
2. Provides better security since the data is accessed through stored procedures instead of accessing them directly.
3. Gives better maintenance since the business logic is tested and stored in the server.

**Merge Join** : Merge Join method takes place when the join is based on the equality condition. Merge Join requires the joining rows to 
be on the same AMP. Rows are joined based on their row hash. Merge Join uses different join strategies to bring the rows to the same AMP.

**Product Join** : Product Join compares each qualifying row from one table with each qualifying row from other table. Product join may 
take place due to some of the following factors −
1. Where condition is missing.
2. Join condition is not based on equality condition.
3. Table aliases is not correct.
4. Multiple join conditions.

**Nested Join** : Nested Join doesn’t use all AMPs. For the Nested Join to take place, one of the condition should be equality on 
the unique primary index of one table and then joining this column to any index on the other table.

**Partitioned Primary Index (PPI)** is an indexing mechanism that is useful in improving the performance of certain queries. When rows 
are inserted into a table, they are stored in an AMP and arranged by their row hash order. When a table is defined with PPI, the rows 
are sorted by their partition number. Within each partition, they are arranged by their row hash. Rows are assigned to a partition based 
on the partition expression defined.
Advantages
1. Avoid full table scan for certain queries.
2. Avoid using secondary index that requires additional physical structure and additional I/O maintenance.
3. Access a subset of a large table quickly.
4. Drop the old data quickly and add new data.

OLAP (**Online Analytical Processing**)functions are similar to aggregate functions except that the aggregate functions will 
return only one value whereas the OLAP function will provide the individual rows in addition to the aggregates.

Following is an example to find the cumulative sum or running total of NetPay on Salary table. Records are sorted by EmployeeNo and 
**cumulative sum** is calculated on NetPay column.

SELECT EmployeeNo, NetPay, 
SUM(Netpay) OVER(ORDER BY EmployeeNo ROWS UNBOUNDED PRECEDING) as TotalSalary 
FROM Salary;

CREATE USER TD01 AS  
PERMANENT = 1000000 BYTES 
PASSWORD = ABC$124 
TEMPORARY = 1000000 BYTES 
SPOOL = 1000000 BYTES;

GRANT SELECT,INSERT,UPDATE ON Employee TO TD01;
REVOKE INSERT,SELECT ON Employee FROM TD01;

FastLoad utility is used to load data into empty tables. Since it does not use transient journals, data can be loaded quickly. It 
doesn't load duplicate rows even if the target table is a MULTISET table.
Limitation

Target table should not have secondary index, join index and foreign key reference.
How FastLoad Works

**FastLoad is executed in two phases**.
Phase 1
1. The Parsing engines read the records from the input file and sends a block to each AMP.
2. Each AMP stores the blocks of records.
3. Then AMPs hash each record and redistribute them to the correct AMP.
4. At the end of Phase 1, each AMP has its rows but they are not in row hash sequence.

Phase 2
1. Phase 2 starts when FastLoad receives the END LOADING statement.
2. Each AMP sorts the records on row hash and writes them to the disk.
3. Locks on the target table is released and the error tables are dropped.

**MultiLoad** can load multiple tables at a time and it can also perform different types of tasks such as 
**INSERT, DELETE, UPDATE and UPSERT**. It can load up to **5 tables at a time and perform up to 20 DML operations** in a script. 
The target table is not required for MultiLoad.

**FastExport** utility is used to export data from Teradata tables into **flat files**. It can also generate the data in report format. 
Data can be extracted from one or more tables using Join. Since FastExport exports the data in **64K blocks**, it is useful for 
extracting large volume of data.

**BTEQ utility** is a powerful utility in Teradata that can be used in both batch and interactive mode. It can be used to run any
DDL statement, DML statement, create Macros and stored procedures. BTEQ can be used to import data into Teradata tables from flat
file and it can also be used to extract data from tables into files or reports.
