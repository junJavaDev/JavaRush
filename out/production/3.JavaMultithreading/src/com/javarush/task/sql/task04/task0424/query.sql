-- Write your code here:
SELECT name, department
FROM employee
WHERE DAYOFYEAR(date_of_birth) <= 200