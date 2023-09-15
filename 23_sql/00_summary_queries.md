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
    order by id // **pivot the cols**

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
