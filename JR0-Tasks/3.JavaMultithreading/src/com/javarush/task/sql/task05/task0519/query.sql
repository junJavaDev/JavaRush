-- Write your code here:
SELECT released, count(*) AS total
FROM lego_set
WHERE number < 10000
GROUP BY released
ORDER BY total DESC