-- category

select * from category;

select a.no, a.blog_id, a.description, a.name, count(b.category_no) as postCount from category a left join post b on a.no = b.category_no where a.blog_id = 1234 group by a.no;

select  a.no, a.blog_id, a.description, a.name, (select count(*) from post b where a.no = b.category_no) as postCount from category a where a.blog_id = '1234';


select * from category where blog_id = 1234;

select no from category where name = 'second' and blog_id = '1234';

insert into category values (null,'second','카테고리입니다','12345');

select * from category where blog_id = 1234;

delete from category where blog_id = '1234' and no = 5;

select no from category where blog_id = 1234 order by no desc limit 1;

