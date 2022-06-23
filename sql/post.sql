-- post

select * from post;

select count(*) from post;

select * from post group by category_no;

insert into post values (null, '하이', 'ㅋㅋ', 16);

select * from post where category_no = 3 order by no desc limit 1;

select * from post a, category b where a.category_no = b.no and b.no = 10;

select  distinct name, (select count(*) from post where category_no = 5) from category;