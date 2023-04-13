-- Write your code here:
SELECT name, count(*)
FROM cars
WHERE prod_year = 2021
GROUP BY name