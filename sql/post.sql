-- post

select * from post where category_no = 10;

insert into post values (null, '하이', 'ㅋㅋ', 16);

select * from post order by no desc limit 1;

select * from post a, category b where a.category_no = b.no and b.no = 4;