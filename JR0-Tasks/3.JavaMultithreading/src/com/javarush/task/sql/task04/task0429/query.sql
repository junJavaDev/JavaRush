-- Write your code here:
SELECT *
FROM event
WHERE date_time BETWEEN DATE_SUB(curdate(), INTERVAL 2 WEEK) AND curdate()