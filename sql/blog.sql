-- blog
select * from blog;

select * from blog where id = '1234';

insert into blog values ('1234','김현석', 'default');

delete from blog where id = 12345; 

update blog set title = '김현석2' where id = 12345;