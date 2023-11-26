-- Write your code here:
select a.full_name, count(distinct p.name) publishers
from author a
         left join book b on a.id = b.author_id
         left join publisher p on p.id = b.publisher_id
group by a.id;