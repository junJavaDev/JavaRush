-- Write your code here:
SELECT prod_year, count(*) AS blue_cars
FROM cars
WHERE name = 'Blue Car'
GROUP BY prod_year