-- Write your code here:
SELECT YEAR(date), MONTH(date), DAY(date), count(*)
    FROM event
WHERE user_id = 3 AND type = 'SOLVE_TASK' AND status = 'OK'
GROUP BY YEAR(date), MONTH(date), DAY(date)