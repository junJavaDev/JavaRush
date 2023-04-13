-- Write your code here:
SELECT a.full_name, count(*) AS books
    FROM author a
        join book b
            ON b.author_id = a.id
    GROUP BY a.full_name
HAVING books > 1


