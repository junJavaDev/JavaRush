-- Write your code here:
SELECT id, identifier, description
FROM parts
ORDER BY identifier ASC, required DESC, description ASC
LIMIT 7
