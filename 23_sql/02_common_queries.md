# Common SQL queries

Link : https://www.linkedin.com/posts/renu-b-600a5823a_sql-questions-activity-7089806957850558464-eMLi/

Note these are Postgres queries

1. **Highest Salary in each department**

```
    CREATE TABLE department(
        ID int,
        SALARY int,
        NAME Varchar(20),
        DEPT_ID Varchar(255)
    );
    INSERT INTO department VALUES (1, 34000, 'ANURAG', 'UI DEVELOPERS'), (2, 33000, 'harsh', 'BACKEND DEVELOPERS'),
    (3, 36000, 'SUMIT', 'BACKEND DEVELOPERS'), (4, 36000, 'RUHI', 'UI DEVELOPERS'), (5, 37000, 'KAE', 'UI DEVELOPERS');

    select DEPT_ID, Name, Salary from department where (DEPT_ID, Salary) in (
        select DEPT_ID, max(salary) from department 
        group by DEPT_ID
    );

```

2. **Highest 3 salaries in each department**

```
    create table Employee(
        ID int,
        Name varchar(50),
        Salary int,
        DepartmentId int
    );
    insert into Employee values
    (1,'Joe',70000,1),
    (2,'Henry',80000,2),
    (3,'Sam',60000,2),
    (4,'Max',90000,1),
    (5,'Janet',69000,1),
    (6,'Randy',85000,1);

    create table Department(
        Id int,
        Name varchar(50)
    );
    insert into Department values
    (1,'IT'),
    (2,'Sales');

    select Ranking.DepartmentId, Ranking.salary, e2.Name from (
        select DepartmentId, salary, rank() over(
            PARTITION BY DepartmentId order by salary desc
        ) as salRank from Employee
        group by DepartmentId, salary
    ) as Ranking 
    inner join Employee as e2
    on  e2.DepartmentId = Ranking.DepartmentId 
    and e2.Salary = Ranking.salary 
    where Ranking.salRank <=3
    order by Ranking.DepartmentId, Ranking.salRank;
    
```

3. **Delete all duplicate emailid**

Delete all the duplicate emails, keeping only one unique email with the smallest id.

```
delete p1 from person p1,person p2 
where p1.email=p2.email and p1.id>p2.id;
```

4. **Rising Temparature**

Write an SQL query to find all dates' Id with higher temperatures compared to its previous dates (yesterday).


```
    select t2.id from Weather as t2, Weather as t1
    where t2.temperature > t1.temperature and t2.recordDate = (t1.recordDate + 1)
```

5. **Atleast 5 classes**

Find all the classes that have at least five students.

```
    select class from (
    select class, count(class) as subjectCount from Courses
    ) as counting 
    where counting.subjectCount >= 5;
```

6. **Tree node**


```
select id,
    (case 
        when p_id is null
        then 'Root'
        when id in (select p_id from Tree as t2)
        then 'Inner'
        else
            'Leaf'
        end
    ) as type
 from Tree as t1;
```

7. **Exchange seat**

Write an SQL query to swap the seat id of every two consecutive students. If the number of students is odd, the id of the last student is not swapped.
```
    select 
    case 
        when id=(select max(id) from seat) and id%2 <> 0 then id
        when id%2 = 0 then id-1
        when id%2 <> 0 then id+1
    end as id, student 
    from seat 
    order by id
```

8. **Actor and Director**

Find all the pairs (actor_id, director_id) where the actor has cooperated with the director at least three times.

```
    with cte as (select actor_id, director_id, count(*) as pair_count
    from ActorDirector
    group by actor_id, director_id)

    select actor_id, director_id from cte
    where pair_count >= 3
```

