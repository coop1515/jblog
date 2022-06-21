-- post

select * from post;

insert into post values (null, '나나', '다다', 3);

select * from post order by no desc limit 1;

select * from post a, category b ;