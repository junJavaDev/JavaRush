-- Write your code here:
SELECT DISTINCT p.name
FROM book b
    LEFT JOIN publisher p ON b.publisher_id = p.id
    LEFT JOIN author a ON author_id = a.id
WHERE a.full_name = 'Mark Twain';
