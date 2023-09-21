## Big Query 

Link : https://learning.oreilly.com/library/view/google-bigquery-the/9781492044451/ch01.html#managed_storage

1. Google BigQuery is a serverless, highly scalable **data warehouse** that comes with a **built-in query engine**. The query engine 
   is capable of running SQL queries on **terabytes of data** in a matter of seconds, and petabytes in only minutes.
2. BigQuery is **serverless**, and you can run queries without the need to **manage infrastructure**. It enables you to carry out 
   analyses that **process aggregations** over the entire dataset **in seconds to minutes**.
3. You didn’t need to **start up a cluster** or **log in** to one.
4. The service scales well even when it does aggregations on terabytes to petabytes of data. This **scalability** is possible because 
   the service distributes the query processing among **thousands of workers almost instantaneously**.
5. Because BigQuery separates compute and storage, it is possible to run BigQuery SQL queries against **CSV (or JSON or Avro)** files 
   that are stored as-is on Google Cloud Storage. this capability is called **federated querying**.

## DBT 

Link : https://www.analytics8.com/blog/dbt-overview-what-is-dbt-and-what-can-it-do-for-my-data-pipeline/#

According to dbt, the tool is a **development framework** that combines modular SQL with software engineering best practices to make 
**data transformation reliable, fast, and fun**.

dbt (data build tool) makes data engineering activities accessible to people with data analyst skills to transform the data in the 
warehouse using **simple select statements**, effectively creating your entire **transformation process with code**. You can write 
**custom business logic using SQL**, **automate data quality testing**, **deploy the code**, and **deliver trusted data** with data 
documentation side-by-side with the code. 

This is more important today than ever due to the shortage of data engineering professionals in the marketplace. Anyone who knows SQL 
can now build **production-grade data pipelines**, reducing the barrier to entry that previously limited staffing capabilities for 
legacy technologies.

The tool acts as an **orchestration layer on top of your data warehouse** to improve and accelerate your data transformation and 
integration process. dbt works by **pushing down your code—doing all** the calculations at the database level—making the entire 
transformation process **faster, more secure, and easier to maintain.**

1. Quickly and easily provide **clean, transformed data** ready for analysis.
2. Apply software engineering practices—such as **modular code, version control**, testing, and continuous integration/continuous 
   deployment (CI/CD)—to analytics code.
3. Build reusable and modular code using **Jinja**.
4. Maintain **data documentation and definitions** within dbt as they build and develop **lineage graphs**.
5. Perform simplified **data refreshes** within dbt Cloud.
6. Perform **automated testing**.

**dbt commands**

Link : https://medium.com/indiciumtech/17-dbt-commands-you-should-start-using-today-581998dbf8f0

1. dbt **init**: Initializes a new dbt project.
2. dbt **run**: Runs all models within the project.
3. dbt **test**: Tests all tests within the project.
4. dbt **snapshot**: Executes snapshots in the snapshots-paths defined in the dbt_project.yml file.
5. dbt **seed**: Loads csv files found in the seed-paths defined in the dbt_project.yml file.
6. dbt **build**: dbt run + dbt test + dbt snapshot + dbt seed (in DAG order).
7. dbt **deps**: Downloads dependencies listed in the packages.yml file.
8. **dbt clean**: This command is helpful in situations where, for any reason, you have to delete the same folders frequently. Just list 
   them in the clean-targets list inside the dbt_project.yml file and let dbt clean do this work for you.
9. **dbt compile**: Have you ever done a “dbt run” and got surprised by compilation errors in some models? You can try “dbt compile” 
   before “dbt run” to catch those errors, this will save you time.
10. **dbt debug**: Shows some useful information about your machine config, such as python and dbt versions, python path, OS info, paths 
    of profiles.yml and dbt_project.yml. It tells you if the profiles.yml and dbt_project.yml were found and if they are valid. It also 
    gives information about the connection and tests it, and informs you if the required dependencies were found.
11. **dbt docs**: One of the most useful commands! That is why I dedicated a space in the cheat sheet for it. It generates your 
    project’s documentation. Documentation is extremely important, especially in large projects! The command can be run in two forms,
    “**dbt docs generate” and “dbt docs serve”**. The first subcommand generates your project’s documentation website and can be used 
    with the –no-compile flag to skip re-compilation. The second subcommand generates documentation locally.

**How to prepare**
1. **Master the SQL**: You should practice creating, modifying, and managing databases. Moreover, you should master data analytics,  
   modeling, and transformation.
2. **Solve coding challenges**: Solve Python, Scala, or C++ coding challenges. Most companies assess programming skills by giving 
   take-home exams and live coding challenges. 
3. **Design an ETL pipeline**: practice to create data, ETL, or delivery pipelines. You need to understand how to test, validate, scale, 
   and maintain data pipelines. 
4. **Analytical engineering**: practice loading, transforming, and data analytics. Learn to create a dashboard for data quality and 
   system performance. 
5. **Review potential questions**: prepare for the interview by reviewing sample mock questions. Get access to hundreds of questions by 
   a simple search on Google. 
6. **Learn about modern data engineering tools**: even if you don’t have experience with modern data engineering tools, you should know 
   how they work and how they integrate with other tools. Companies are always looking for better tools that can improve performance at 
   a lower cost.
7. **Learn batch processing and streaming**: Apache Spark is used for batch processing, and Apache Kafka is used for data streaming. 
   These tools are in high demand, and they will help you land a job in the top companies.
8. **Environment**: In some cases, the interviewer will ask about cloud computing (GCP, AWS, Azure), Docker, scripting, Terraform, and 
   Kubernetes. You can use these tools to set up cloud or on-premise computer and storage resources. Understanding these technologies 
   and integrating them into portfolio projects is a good practice. 

## SQL

In SQL, a **view is a virtual table based on the result-set** of an SQL statement.
A view contains **rows and columns, just like a real table.** The fields in a view are fields from one or more real tables
in the database.
You can add SQL statements and functions to a view and present the data as if the data were coming **from one single table**.

```sql
CREATE OR REPLACE VIEW view_name AS
SELECT column1, column2, ...
FROM table_name
WHERE condition; 
```

**Views**: A View is a **virtual relation that acts** as an actual relation. It is not a part of logical relational model of the 
database system. Tuples of the view are not stored in the database system and **tuples of the view are generated every time** the
view is accessed. Query expression of the view **is stored in the databases system**. Views can be used everywhere were we can use the 
actual relation. Views can be used to create **custom virtual relations** according to the needs of a specific user. We can create as 
**many views as we want** in a databases system. 

**Materialized Views**: When the results of a view expression are **stored in a database system**, they are called materialized
views. SQL does not provides any standard way of defining materialized view, however some database management system provides 
**custom extensions to use materialized views**. The process of keeping the materialized views updated is know as view maintenance. Database system uses one of the three ways to keep the materialized view updated:

1. Update the materialized view **as soon as** the relation on which it is defined is updated.
2. Update the materialized view every time the view is **accessed**.
3. Update the materialized view **periodically**.

Materialized view is useful when the view is accessed frequently, as it saves the computation time, as the result are stored in the 
database before hand. Materialized view can also be helpful in case where the **relation on which view is defined is very large** and 
**the resulting relation of the view is very small**. Materialized view has **storage cost and updation overheads** associated with it. 

```sql
CREATE MATERIALIZED VIEW view_name
AS
query
WITH [NO] DATA;

REFRESH MATERIALIZED VIEW view_name;

REFRESH MATERIALIZED VIEW CONCURRENTLY view_name;
```

To load data into a materialized view, you use the REFRESH MATERIALIZED VIEW statement as shown below.
When you refresh data for a materialized view, PostgreSQL locks the entire table therefore you cannot query data against it. To avoid 
this, you can use the CONCURRENTLY option.
With CONCURRENTLY option, PostgreSQL creates a temporary updated version of the materialized view, compares two versions, and performs 
**INSERT and UPDATE** only the differences.
You can query against a materialized view while it is being updated. One requirement for using **CONCURRENTLY** option is that the 
materialized view must have a **UNIQUE** index.

The table and incremental materializations **persist a table**, the view materialization creates a **view**, and the 
ephemeral materialization, instead of **persisting anything, returns results directly using a common table expression (CTE)**

## SQL JOINS

There is no difference between **LEFT JOIN and LEFT OUTER JOIN, they are exactly same.**

At the top level there are mainly 3 types of joins:
1. INNER JOIN fetches data if present in **both the tables**.

OUTER JOINs are of 3 types:
   LEFT OUTER JOIN - fetches data if present in the **left table**.
   RIGHT OUTER JOIN - fetches data if present in the **right table**.
   FULL OUTER JOIN - fetches data if present in **either of the two tables**.

CROSS JOIN, as the name suggests, does n times m pairings that join everything to everything. That is similar to where we simply list the tables for joining (in the FROM clause of the SELECT statement), using commas to separate them.

Points to be noted:
1. If you just mention **JOIN then by default it is an INNER JOIN.**
2. An **OUTER join has to be LEFT | RIGHT | FULL;** you can not simply say OUTER JOIN.
3. You can drop the **OUTER keyword and just say LEFT JOIN or RIGHT JOIN or FULL JOIN**.
