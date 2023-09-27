## ETL pipeline

Link : https://panoply.io/data-warehouse-guide/3-ways-to-build-an-etl-process/

ETL (Extract, Transform, Load) is an automated process which takes raw data, extracts the **information required** for analysis, 
**transforms** it into a format that can serve business needs, and **loads it to a data warehouse**. ETL typically summarizes
data to reduce its size and improve performance for specific types of analysis.

To build an ETL pipeline with **batch processing**, you need to:
1. **Create reference data**: create a dataset that defines the set of permissible values your data may contain. For example, 
   in a country data field, specify the list of country codes allowed.
2. **Extract data from different sources**: the basis for the success of subsequent ETL steps is to extract data correctly.
  Take data from a range of sources, such as APIs, non/relational databases, XML, JSON, CSV files, and convert it into a single
  format for standardized processing.
3. **Validate data**: Keep data that have values in the expected ranges and reject any that do not. For example, if you only want dates 
   from the last year, reject any values older than 12 months. Analyze rejected records, on an on-going basis, to identify issues, 
   correct the source data, and modify the extraction process to resolve the problem in future batches.
4. **Transform data**: Remove duplicate data (cleaning), apply business rules, check data integrity (ensure that data has not been 
   corrupted or lost), and create aggregates as necessary. For example, if you want to analyze revenue, you can summarize the dollar 
   amount of invoices into a daily or monthly total. You need to program numerous functions to transform the data automatically. 
5. **Stage data**: You do not typically load transformed data directly into the target data warehouse. Instead, data first enters a 
   staging database which makes it easier to roll back if something goes wrong. At this point, you can also generate audit reports for 
   regulatory compliance, or diagnose and repair data problems.
6. **Publish to your data warehouse**: Load data to the target tables. Some data warehouses overwrite existing information whenever the 
   ETL pipeline loads a new batch - this might happen daily, weekly, or monthly. In other cases, the ETL workflow can add data without 
   overwriting, including a timestamp to indicate it is new. You must do this carefully to prevent the data warehouse from “exploding” 
   due to disk space and performance limitations.

To build a stream processing **ETL pipeline** with Kafka, you need to:
1. **Extract data into Kafka**: the Confluent JDBC connector pulls each row of the source table and writes it as a key/value pair into a 
   Kafka topic (a feed where records are stored and published). Applications interested in the state of this table read from this topic. 
   As client applications add rows to the source table, Kafka automatically writes them as new messages to the Kafka topic, enabling a 
   real-time data stream. Note you can implement a database connection yourself without Confluent’s commercial product.
2. **Pull data from Kafka topics**: the ETL application extracts messages from the Kafka topic as Avro records, creates an Avro schema 
   file, and deserializes them. Then it creates KStream objects from the messages.
3. **Transform data in KStream objects**: with the Kafka Streams API, the stream processor receives one record at a time, processes it, 
   and produces one or more output records for downstream processors. These processors can transform messages one at a time, filter them 
   based on conditions, and perform data operations on multiple messages such as aggregation.
4. **Load data to other systems**: the ETL application still holds the enriched data, and now needs to stream it into target systems, 
    such as a data warehouse or data lake. In Confluent’s example, they propose using their S3 Sink Connector to stream the data to 
    Amazon S3. You can also integrate with other systems such as a Redshift data warehouse using Amazon Kinesis.

In the **Extract Load Transform (ELT)** process, you first extract the data, and then you immediately move it into a centralized
data repository. After that, data is transformed as needed for downstream use. This method gets data in **front of analysts** much 
faster than ETL while simultaneously simplifying the architecture.

Moreover, today’s cloud data warehouse and data lake infrastructure support ample storage and scalable computing power. Thus, it’s no 
longer necessary to prevent the data warehouse from **“exploding”** by keeping **data small** and summarized through transformations 
before loading. It’s possible to maintain massive data pools in the cloud at a **low cost** while leveraging ELT tools to speed up and simplify data processing.


To build a data pipeline without ETL in **Panoply**, you need to:
1. **Select data sources and import data**: select data sources from a list, enter your credentials and define destination tables. 
   Click “Collect,” and Panoply automatically pulls the data for you. Panoply automatically takes care of schemas, 
   data preparation, data cleaning, and more.
2. **Run transformation queries**: select a table and run an SQL query against the raw data. You can save the query as a 
   transformation, or export the resulting table as a CSV. You can run several transformations until you achieve the data format 
   you need. You shouldn’t be concerned about “ruining” the data - Panoply lets you perform any transformation, but keeps your 
   raw data intact.
3. **Perform data analysis with BI tools**: you can now connect any BI tool such as **Tableau or Looker** to Panoply and explore the 
   transformed data.


1. **Traditional ETL batch processing** - meticulously preparing and transforming data using a rigid, structured process.
2. **ETL with stream processing** - using a modern stream processing framework like Kafka, you pull data in real-time from source, 
   manipulate it on the fly using Kafka’s Stream API, and load it to a target system such as Amazon Redshift.
3. **Automated data pipeline without ETL** - use Panoply’s automated data pipelines, to pull data from multiple sources, 
   automatically prep it without requiring a full ETL process, and immediately begin analyzing it using your favorite BI tools. 
   Get started with Panoply in minutes.

