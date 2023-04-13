-- Write your code here:
SELECT name, count(*) AS car_count
FROM cars
GROUP BY name
HAVING car_count < 3