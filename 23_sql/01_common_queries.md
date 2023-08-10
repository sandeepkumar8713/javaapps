# Common SQL queries

Link : https://www.linkedin.com/posts/renu-b-600a5823a_sql-questions-activity-7089806957850558464-eMLi/

Note these are Postgres queries

1. **Combine two tables**

```
    CREATE TABLE Orders (
        orderId int,
        CustomerID int,
        OrderDate Date
    );
    INSERT INTO Orders (orderId, CustomerID, OrderDate) VALUES (10308, 2, '1996-09-18');
    INSERT INTO Orders (orderId, CustomerID, OrderDate) VALUES (10309, 37, '1996-09-19'); 
    INSERT INTO Orders (orderId, CustomerID, OrderDate) VALUES (10310, 77, '1996-09-20'); 


    CREATE TABLE Customers (
        CustomerID int,
        CustomerName Varchar,
        ContactName Varchar,
        Country Varchar
    );
    INSERT INTO Customers (CustomerID, CustomerName, ContactName, Country) VALUES (1, 'Alfreds Futterkiste','Maria Anders', 'Germany');
    INSERT INTO Customers (CustomerID, CustomerName, ContactName, Country) VALUES (2, 'Ana Trujillo Emparedados y helados','Ana Trujillo', 'Mexico');
    INSERT INTO Customers (CustomerID, CustomerName, ContactName, Country) VALUES (3, 'Antonio Moreno Taquería','Antonio Moreno', 'Mexico');

    SELECT Orders.orderId, Customers.CustomerName, Orders.OrderDate
    FROM Orders
    INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;

```

2. **Nth Highest Salary**

```
    CREATE TABLE emp (
    emp_name VARCHAR(50),
    emp_salary DECIMAL(10,2)
    );
    INSERT INTO emp (emp_name, emp_salary) VALUES
    ('Shubham Thakur', 50000.00),
    ('Aman Chopra', 60000.50),
    ('Naveen Tulasi', 75000.75),
    ('Bhavika uppala', 45000.25),
    ('Nishant jain', 80000.00);

    //4th Highest
    Select emp_salary from emp 
    order by emp_salary DESC OFFSET 3 LIMIT 1;

    //2nd Highest
    Select emp_salary from emp 
    order by emp_salary DESC OFFSET 1 LIMIT 1;
```

3. **Rank Scores**
We will use window function

```
    CREATE TABLE sales (
	    v VARCHAR(10)
    );

    INSERT INTO sales(v)
    VALUES('A'),('B'),('B'),('C'),('C'),('D'),('E');

    SELECT
	    v,
	    RANK () OVER ( 
	    	ORDER BY v 
	    ) rank_no 
    FROM sales;
```

4. **Consecutive Numbers**

Write a SQL query to find the marks, which appear at least thrice one after another without interruption.

```
    CREATE TABLE IF NOT EXISTS logs (student_id int, marks int);
    INSERT INTO logs (student_id, marks) VALUES ('101', '83'),('102', '79'),('103', '83'), ('104', '83'), ('105', '83'), ('106', '79'),
    ('107', '79'), ('108', '83');

    SELECT DISTINCT L1.marks AS  ConsecutiveNums
    FROM (logs L1 JOIN logs L2 ON L1.marks = L2.marks AND L1.student_id = L2.student_id-1)
    JOIN logs L3 ON L1.marks = L3.marks AND L2.student_id = L3.student_id-1;
```

5. **Higher Salary than manager**
Write an SQL query to find the employees who earn more than their managers.

```

    CREATE TABLE employee_1 (
	    id int,
        Name varchar,
        Salary DECIMAL(10,2),
        managerId int
    );

    insert into employee_1(id, Name, Salary, managerId) values(1, 'Joe', 70000.0,3), (2, 'Henry', 80000.0, 4),
    (3,'Sam',60000.0,NULL), (4,'Max',90000.0,NULL);

    SELECT e1.name FROM employee_1 e1 
    JOIN employee_1 e2 ON e1.ManagerId = e2.Id 
    WHERE e1.salary > e2.salary;
```

5. **Duplicate Emails**

Write a SQL query to find all the duplicate emails (no upper case letters) of the employees. Return email ids.

```
    CREATE TABLE IF NOT EXISTS employees(employee_id int, employee_name varchar(255), email_id varchar(255));
    INSERT INTO employees (employee_id,employee_name, email_id) VALUES ('101','Liam Alton', 'li.al@abc.com'),
    ('102','Josh Day', 'jo.da@abc.com'), ('103','Sean Mann', 'se.ma@abc.com'), ('104','Evan Blake', 'ev.bl@abc.com'), ('105','Toby Scott', 'jo.da@abc.com');

    select email_id from (
        select email_id, count(email_id) as email_count from employees
        group by email_id
    ) as countEmail where email_count > 1;

    select email_id from (
        select email_id, count(email_id) as email_count from employees
        group by email_id
        having email_count > 1
    );
```

6. **Customer who never orders**

Find all customers who never order anything.

```
    create Table Customers_1(
        id int primary key,
        name varchar
    );
    insert into Customers_1(id, name) values(1,'Joe'), (2, 'Henry'), (3, 'Sam'), (4, 'Max');

    create Table Orders_1(
        id int primary key,
        customerId int,
        FOREIGN KEY (customerId) REFERENCES Customers_1(id)
    );
    insert into Orders_1(id, customerId) values(1, 3), (2, 1);

    select name from Customers_1 where id not in (select customerId from Orders_1);

    delete from Orders_1;

```
