-- Write your code here:
SELECT YEAR(prod_date) AS prod_year, MONTH(prod_date) AS prod_month, count(*)
FROM cars
GROUP BY prod_year, prod_month