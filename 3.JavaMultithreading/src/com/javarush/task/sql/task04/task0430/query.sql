-- Write your code here:
SELECT YEAR(date), MONTH(date), DAY(date), count(*) AS total
FROM event
GROUP BY YEAR(date), MONTH(date), DAY(date)
HAVING total > 5