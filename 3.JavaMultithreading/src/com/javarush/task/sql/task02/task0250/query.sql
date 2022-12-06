-- Write your code here:
SELECT department, position, count(*) AS total
FROM employee
GROUP BY department, position
HAVING total > 1 AND position = 'frontend developer'
LIMIT 1