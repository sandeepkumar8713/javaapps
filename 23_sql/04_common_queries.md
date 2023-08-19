# Common SQL queries

Link : https://www.linkedin.com/posts/renu-b-600a5823a_sql-questions-activity-7089806957850558464-eMLi/

Note these are Postgres queries

1. **Visited with no transcations**

Write a SQL query to find the IDs of the users who visited without making any transactions and the number of times they made these types of visits.

```
    select v1.customer_id, count(*) as count_no_trans
    from Visits as v1
    where v1.visit_id not in (select visit_id from Transactions)
    Group by v1.customer_id
```

2. **Fix names in table**

Fix the names so that only the first character is uppercase and the rest are lowercase.
Return the result table ordered by user_id.

```
    select u.user_id, 
        concat(upper(substr(name,1,1)),lower(substr(name,2))) 
    as name 
    from users u 
    order by u.user_id;
```

3. **Time spent by employee**

Calculate the total time in minutes spent by each employee on each day at the office. Note that within one day, an employee can enter 
and leave more than once. The time spent in the office for a single entry is out_time - in_time.

```
    select event_day as day, emp_id,
        sum(out_time-in_time) as total_time
    from Employees
    group by event_day, emp_id
```

4. **Calculate special bonus**

Calculate the bonus of each employee. The bonus of an employee is 100% of their salary if the ID of the employee is an odd number and the employee name does not start with the character 'M'. The bonus of an employee is 0 otherwise.

```
    select employee_id,
        (case
            when employee_id%2 = 1 and substring(name,1,1) != 'M'
            then salary
            else
                0
        end) as bonus
    from Employees
```

5. **Latest login in 2020**

Write an SQL query to report the latest login for all users in the year 2020. Do not include the users who did not login in 2020.

```
    select user_id, max(time_stamp) as last_stamp
    from Logins
    where time_stamp >= '2020-01-01' and time_stamp <= '2020-12-31'
    group by user_id
```

6. **Missing information**

Write an SQL query to report the IDs of all the employees with missing information. The information of an employee is missing if:
The employee's name is missing, or
The employee's salary is missing.

```
    with all_employee_ids as (
        select employee_id from Employees
        union
        select employee_id from Salaries
    )

    select employee_id from all_employee_ids 
    where employee_id not in (select employee_id from Employees)

    union 

    select employee_id from all_employee_ids 
    where employee_id not in (select employee_id from Salaries)
```

11. **Get next days**

```
    SELECT current_date, 'Today'
    UNION ALL
    SELECT current_date + interval '10 days', '10 days later'
    UNION ALL
    SELECT current_date - interval '10 days', '10 days Earlier'
    UNION ALL
    SELECT current_date + interval '1 months', 'Next Month'
    UNION ALL
    SELECT current_date - interval '1 months', 'Previous Month'
    UNION ALL
    SELECT current_date + interval '1 year', 'Next Year';
```

12. **Windows function**
Calculate 7 day moving average from a table that contains price and date timestamps using window functions

```
    select *,
    avg(Price) OVER(ORDER BY Date
        ROWS BETWEEN 6 PRECEDING AND CURRENT ROW )
        as moving_average
    from stock_price;
```

13. **Use having**

```
    SELECT COUNT(CustomerID), Country
    FROM Customers
    GROUP BY Country
    HAVING COUNT(CustomerID) > 5
    ORDER BY COUNT(CustomerID) DESC;
```

14. **Record of last 30 days**

```
    select * from sample_table 
    where sent_date between current_date - interval '30 days' and current_date;

    SELECT * FROM Products
    WHERE Price BETWEEN 10 AND 20;
```

## Notes
1. SELECT v, RANK () OVER ( ORDER BY v ) rank_no FROM sales; // **Ranking**

2. Date, avg(Price) OVER(ORDER BY Date desc ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) // **Window function**

3. GROUP_CONCAT(DISTINCT product order by product separator ',') as products // **Group row value as a list**

4. GROUP BY Country HAVING COUNT(CustomerID) > 5 // **where clause on aggregate**

5. conditions like 'DIAB100 %' // **Match pattern**

6. concat(upper(substr(name,1,1)),lower(substr(name,2)))  // **Substring and upper case**

7. union all  // **allows repetition of rows**

8. order by emp_salary DESC OFFSET 3 LIMIT 1; // **4th hihgest salary**

9. rank() over(PARTITION BY DepartmentId order by salary desc) as salRank  // **Rank salary in each dept**

10. where sent_date between current_date - interval '30 days' and current_date; // **Data for last 30 days**
