-- Write your code here:
SELECT b.publication_year
     , b.isbn
     , b.title
     , a.full_name AS author
     , p.name AS publisher
FROM book b
    LEFT JOIN author a on b.author_id = a.id
    LEFT JOIN publisher p on b.publisher_id = p.id
