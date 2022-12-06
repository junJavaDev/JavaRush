-- Write your code here:
SELECT name AS car_name, count(*) AS car_count
FROM cars
GROUP BY car_name