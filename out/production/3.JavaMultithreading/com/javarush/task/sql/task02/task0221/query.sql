-- Write your code here:
SELECT prod_year, count(*)
FROM cars
GROUP BY prod_year