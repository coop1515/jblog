-- category

select * from category;

insert into category values (null,'second','카테고리입니다','1234');

select * from category where blog_id = 1234;

delete from category where blog_id = '12345';