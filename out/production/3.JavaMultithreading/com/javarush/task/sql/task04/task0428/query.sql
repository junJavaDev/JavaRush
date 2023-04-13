-- Write your code here:
SELECT *
FROM event
WHERE YEAR(CURDATE()) = YEAR(date_time)
  AND MONTH(CURDATE()) = MONTH(date_time)