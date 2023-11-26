-- Write your code here:
SELECT released, count(*) AS total
FROM lego_set
GROUP BY released
ORDER BY total DESC