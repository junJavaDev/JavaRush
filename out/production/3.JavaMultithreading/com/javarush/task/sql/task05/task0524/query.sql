-- Write your code here:
SELECT b.isbn, b.title
FROM book b LEFT JOIN author a on a.id = b.author_id
WHERE a.last_name LIKE 'S%'

