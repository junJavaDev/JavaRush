-- Write your code here:
UPDATE employee e
    SET e.salary = e.salary + 1000
        WHERE   e.id IN (select employee_id from task where task.exp_date > '2022-10-01')
