-- Write your code here:
SELECT is_full_time, count(*)
    FROM students
GROUP BY is_full_time
