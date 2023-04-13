-- Write your code here:
SELECT MONTHNAME(date), count(*)
    FROM event
WHERE status = 'ERROR' OR status = 'FAILED'
GROUP BY MONTHNAME(date)
ORDER BY count(*) DESC
LIMIT 1