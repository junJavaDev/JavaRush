-- Write your code here:
SELECT department, position, count(*)
FROM employee
GROUP BY department, position