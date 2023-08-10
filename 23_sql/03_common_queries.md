# Common SQL queries

Link : https://www.linkedin.com/posts/renu-b-600a5823a_sql-questions-activity-7089806957850558464-eMLi/

Note these are Postgres queries

1. **Sales Anaylist 3**

Write an SQL query that reports the products that were only sold in the first quarter of 2019. That is, between 2019-01-01 and 2019-03-31 inclusive.

```
    select p1.product_id, p1.product_name 
    from Product as p1 inner join Sales as s1
    on p1.product_id = s1.product_id
    where '2019-01-01' <= s1.sale_date and '2019-03-31' >= s1.sale_date
    and p1.product_id not in 
    (select product_id from Sales where '2019-01-01' > sale_date or '2019-03-31' < sale_date)

```

2. **Game Play Analysis I**

Find the first login date for each player.

```
    select player_id, min(event_date) as first_login
    from Activity
    Group by player_id
```

3. **Market Analysis I**

Write an SQL query to find for each user, the join date and the number of orders they made as a buyer in 2019.


```
    with cte as (
    select buyer_id, count(*) as order_count
    from Orders
    where order_date >= '2019-01-01' and order_date <= '2019-12-31'
    group by buyer_id
    )

    select user_id as buyer_id, join_date, 
    (case 
        when  c1.order_count is null
        then 0
        else
        c1.order_count
    end) as orders_in_2019
    from Users as u1 left outer join cte as c1
    on u1.user_id = c1.buyer_id
```

4. **Reformat Table**

Write an SQL query to reformat the table such that there is a department id column and a revenue column for each month.

**Pivot** the cols.

```
    select id, 
        sum(case when month = 'jan' then revenue else null end) as Jan_Revenue,
        sum(case when month = 'feb' then revenue else null end) as Feb_Revenue,
        sum(case when month = 'mar' then revenue else null end) as Mar_Revenue,
        sum(case when month = 'apr' then revenue else null end) as Apr_Revenue,
        sum(case when month = 'may' then revenue else null end) as May_Revenue,
        sum(case when month = 'jun' then revenue else null end) as Jun_Revenue,
        sum(case when month = 'jul' then revenue else null end) as Jul_Revenue,
        sum(case when month = 'aug' then revenue else null end) as Aug_Revenue,
        sum(case when month = 'sep' then revenue else null end) as Sep_Revenue,
        sum(case when month = 'oct' then revenue else null end) as Oct_Revenue,
        sum(case when month = 'nov' then revenue else null end) as Nov_Revenue,
        sum(case when month = 'dec' then revenue else null end) as Dec_Revenue
    from department
    group by id
    order by id
```

5. **capital-gainloss**

Write an SQL query to report the Capital gain/loss for each stock.

The Capital gain/loss of a stock is the total gain or loss after buying and selling the stock one or many times.
```
    SELECT stock_name, SUM(
        CASE
            WHEN operation = 'Buy' THEN -price
            ELSE price
        END
    ) AS capital_gain_loss
    FROM Stocks
    GROUP BY stock_name
```

6. **Top Travellers**

Write an SQL query to report the distance traveled by each user.

Return the result table ordered by travelled_distance in descending order, if two or more users traveled the same distance, 
order them by their name in ascending order.

```
    with cte as (
    select r1.user_id, sum(r1.distance) as travelled_distance
    from Rides as r1
    group by r1.user_id
<<<<<<< HEAD

=======
>>>>>>> develop
    )

    select u1.name, 
    (case 
        when travelled_distance is null
        then 0
        else travelled_distance
    end) as travelled_distance
    from Users as u1 left outer join cte as r1 
    on u1.id = r1.user_id
    order by travelled_distance desc, u1.name asc
```

7. **Group by product sale** 

Find for each date the number of different products sold and their names.
The sold products names for each date should be sorted lexicographically.

```
    select sell_date, 
        count( DISTINCT product ) as num_sold,
        GROUP_CONCAT( DISTINCT product order by product separator ',' ) as products
    FROM Activities 
    GROUP BY sell_date 
    order by sell_date;
```

8. **Patient with a condition** 

Find the patient_id, patient_name and conditions of the patients who have Type I Diabetes. Type I Diabetes always starts with DIAB1 prefix.

```
    select * 
    from Patients 
    where conditions like 'DIAB100 %' or conditions like '% DIAB100' 
```

<<<<<<< HEAD
9. 
=======
>>>>>>> develop
