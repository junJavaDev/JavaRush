-- Write your code here:
SELECT name, count(*)
FROM cars
GROUP BY name
HAVING count(*) > 1