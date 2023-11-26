-- Write your code here:
SELECT department, count(*) AS number_of_employees
FROM employee
GROUP BY department