1. **find the start and end number of continuous ranges in table Logs.**

We are making groups by subtracting ROW_NUMBER from log-id. 

https://www.db-fiddle.com/f/ePzqZ6bNRYP2G2BoTSkXkc/0

with cte as (
  SELECT 
	log_id,
	log_id-ROW_NUMBER() OVER (ORDER BY log_id) AS diff
  FROM t1
)

SELECT 
	MIN(log_id) AS start_id,
	MAX(log_id) AS end_id
from cte
group by diff
order by start_id

2. **Find candidate with highest vote**

SELECT Name
FROM Candidate
WHERE id = (SELECT CandidateId FROM Vote
    GROUP BY CandidateId
    ORDER BY COUNT(1) desc
    LIMIT 1)                                           

