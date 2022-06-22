-- category
select * from category;
select no from category where name = 'second' and blog_id = '1234';

insert into category values (null,'second','카테고리입니다','12345');

select * from category where blog_id = 1234;

delete from category where blog_id = '1234' and no = 5;

select no from category where blog_id = 1234 order by no desc limit 1;