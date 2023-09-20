## Big Query 

Link : https://learning.oreilly.com/library/view/google-bigquery-the/9781492044451/ch01.html#managed_storage

1. Google BigQuery is a serverless, highly scalable **data warehouse** that comes with a **built-in query engine**. The query engine 
   is capable of running SQL queries on **terabytes of data** in a matter of seconds, and petabytes in only minutes.
2. BigQuery is **serverless**, and you can run queries without the need to **manage infrastructure**. It enables you to carry out 
   analyses that **process aggregations** over the entire dataset **in seconds to minutes**.
3. You didn’t need to **start up a cluster** or log in to one.
4. The service scales well even when it does aggregations on terabytes to petabytes of data. This **scalability** is possible because 
   the service distributes the query processing among **thousands of workers almost instantaneously**.
5. Because BigQuery separates compute and storage, it is possible to run BigQuery SQL queries against **CSV (or JSON or Avro)** files 
   that are stored as-is on Google Cloud Storage. this capability is called **federated querying**.

## DBT 

Link : https://www.analytics8.com/blog/dbt-overview-what-is-dbt-and-what-can-it-do-for-my-data-pipeline/#

According to dbt, the tool is a **development framework** that combines modular SQL with software engineering best practices to make **data transformation reliable, fast, and fun**.

dbt (data build tool) makes data engineering activities accessible to people with data analyst skills to transform the data in the 
warehouse using **simple select statements**, effectively creating your entire **transformation process with code**. You can write 
**custom business logic using SQL**, **automate data quality testing**, **deploy the code**, and **deliver trusted data** with data 
documentation side-by-side with the code. 

This is more important today than ever due to the shortage of data engineering professionals in the marketplace. Anyone who knows SQL 
can now build **production-grade data pipelines**, reducing the barrier to entry that previously limited staffing capabilities for legacy technologies.

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
