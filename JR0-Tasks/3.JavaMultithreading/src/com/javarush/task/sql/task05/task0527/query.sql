-- Write your code here:
SELECT distinct title
FROM book b
    JOIN author a on a.id = b.author_id
WHERE a.full_name = 'Edgar Allan Poe';
