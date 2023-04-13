-- Write your code here:
SELECT department AS department_name, count(*) AS count
FROM employee
WHERE position = 'frontend developer'
GROUP BY department