create table member(
	user_id varchar(30) primary key,
	name varchar(30) not null,
	age int not null,
	joindate timestamp
);

select * from member;