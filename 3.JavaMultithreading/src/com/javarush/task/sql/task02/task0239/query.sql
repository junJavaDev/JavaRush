-- Write your code here:
SELECT YEAR(date_of_birth), MONTH(date_of_birth)
FROM employee
WHERE MONTH(date_of_birth) IN (12, 1, 2)