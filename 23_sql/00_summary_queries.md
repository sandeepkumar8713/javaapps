## Notes

1. SELECT v, RANK () OVER ( ORDER BY v ) rank_no FROM sales; // **Ranking**

2. Date, avg(Price) OVER(ORDER BY Date desc ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) // **Window function**

3. GROUP_CONCAT(DISTINCT product order by product separator ',') as products // **Group row value as a list**

4. GROUP BY Country HAVING COUNT(CustomerID) > 5 // **where clause on aggregate**

5. conditions like 'DIAB100 %' // **Match pattern**

6. concat(upper(substr(name,1,1)),lower(substr(name,2)))  // **Substring and upper case**

7. union all  // **allows repetition of rows**

8. order by emp_salary DESC OFFSET 3 LIMIT 1; // **4th hihgest salary**

9. select salary, rank() over(PARTITION BY DepartmentId order by salary desc) as salRank  // **Rank salary in each dept**

10. where sent_date between current_date - interval '30 days' and current_date; // **Data for last 30 days**

11. select DEPT_ID, Name, Salary from department where (DEPT_ID, Salary) in (
        select DEPT_ID, max(salary) from department 
        group by DEPT_ID                              //**Highest salary in each dept**
    );                    

12. SELECT Orders.orderId, Customers.CustomerName, Orders.OrderDate
    FROM Orders
    INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;  // **join statement**

13. DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste'; // **Delete row**
    DROP TABLE Customers; // **delete table**

13. delete p1 from person p1,person p2 
    where p1.email=p2.email and p1.id>p2.id; // **delete duplicate**

14. select id, 
        sum(case when month = 'jan' 
            then revenue 
            else null 
            end) as Jan_Revenue
    from department
    group by id
    order by id                             // **pivot the cols**

15. select product_id from Sales where '2019-01-01' > sale_date or '2019-03-31' < sale_date // **compare date**

16. SELECT SUBSTRING(Address, 1, CHARINDEX('(',Address)) FROM EmployeeInfo;  // **string before brackets in address**

17. CREATE TABLE NewTable AS SELECT * FROM EmployeeInfo; // **copy table**

18. SELECT * FROM EmpPosition ORDER BY Salary DESC LIMIT N;  // **top n records**

19. SELECT * FROM EmployeeInfo WHERE EmpLname LIKE '____a'; // **5 alphabets, ending a**

20. SELECT EmpID FROM (SELECT rowno, EmpID from EmployeeInfo) WHERE MOD(rowno,2)=0; // **select even rows**

21. SELECT EmpID, EmpFname, Department COUNT(*) 
    FROM EmployeeInfo 
    GROUP BY EmpID, EmpFname, Department HAVING COUNT(*) > 1; // **find duplicate records**

22. SELECT Email FROM EmployeeInfo 
    WHERE NOT REGEXP_LIKE(Email, ‘[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]{2,4}’, ‘i’); // **validate email**

23. select t_1.*, lead(year, 2) over (partition by name order by year) as end_year
    from participation as t_1;                                                  // **get next 2 consecutive years**

24. select id, name, 
	(case 
     when 0 <= avg_score.a_score and avg_score.a_score <= 20
     then 'Hard'
     when 20 < avg_score.a_score and avg_score.a_score <= 60
     then 'Medium' 
     when 60 < avg_score.a_score 
     then 'Easy'
     else Null
     end
    ) as difficulty
    from tasks join avg_score on tasks.id = avg_score.task_id
    order by tasks.id                                                       // **Multiple case statements**

25. delete p1 from new_table p1, trips p2
    where p1.source = p2.destination and p1.destination = p2.source and 
    p1.rowno > p2.rowno;                                                   // **delete rows with opposite dirtection**

26. select Ranking.DepartmentId, Ranking.salary, e2.Name from (
        select DepartmentId, salary, rank() over(
            PARTITION BY DepartmentId order by salary desc
        ) as salRank from Employee
        group by DepartmentId, salary
    ) as Ranking 
    inner join Employee as e2
    on  e2.DepartmentId = Ranking.DepartmentId 
    and e2.Salary = Ranking.salary 
    where Ranking.salRank <=3
    order by Ranking.DepartmentId, Ranking.salRank;                       // **Top 3 salaries in each dept**

27. SELECT Request_at AS Day,
    ROUND(SUM(IF(Status<>"completed", 1, 0))/COUNT(Status),2) AS "Cancellation Rate"
    FROM Trips
    GROUP BY Request_at                          // **Cancelation rate(total cancelation / total user on that day)**

28. select avg(num)     // https://www.db-fiddle.com/f/9YLGnWc5ctJGNWJfkT5STH/0
    from (
    select 
        t1.*,
        sum(freq) over (order by num asc) as cumm_sum,
        sum(freq) over () as total
    from t1) t2
    where total <= 2 * cumm_sum and
        total >= 2 * (cumm_sum - freq);               // **Find median from freq**



30. SELECT IFNULL((round(accepts/requests, 2)), 0.0) AS accept_rate
    FROM                                                            // **count request acceptance ratio**
        (SELECT count(DISTINCT sender_id, send_to_id) AS requests FROM friend_request) AS t1,
        (SELECT count(DISTINCT requester_id, accepter_id) AS accepts FROM request_accepted) AS t2 

31. SELECT *                         // https://dbfiddle.uk/mTxWrpVj
    FROM   crosstab(
        'SELECT section, status, ct
        FROM   tbl
        ORDER  BY 1,2'
   ) AS ct ("Section" text, "Active" int, "Inactive" int);  // **Pivot status and cnt**

32. select row_number() OVER () as rownum, name, year 
    from participation order by name;                      // **select row number in psql**

33. COALESCE(bb.total_amount, 0) total_amount             // **Return first not null number**

34. WHERE extract('year' FROM order_date) = 2019          // **Match only year date**

35. https://www.db-fiddle.com/f/uxV69xBAXhhfJXQ99QSrd5/0
    select product_id, max(change_date) as max_data
    from Products
    where change_date <= '2019-08-16' 
    group by product_id                                  // **Aggregate function on date column**

36. https://www.dsfaisal.com/articles/sql/leetcode-sql-problem-solving#1445-apples--oranges--medium---leetcode

37. CREATE TABLE Orders (
        orderId int,
        CustomerID int,
        OrderDate Date
    );                                                  // **Create Table**

38. SELECT EmployeeNo, NetPay, 
    SUM(Netpay) OVER(ORDER BY EmployeeNo ROWS UNBOUNDED PRECEDING) as TotalSalary 
    FROM Salary;                                        // **Cummulative Sum**
