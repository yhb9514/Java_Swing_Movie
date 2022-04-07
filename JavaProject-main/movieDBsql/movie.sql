create table member(
id varchar(20) not null primary key,
name varchar(20) not null,
pwd varchar(20) not null,
phoneNumber varchar(20) not null
);

insert into member values('admin', '관리자', '1234', '01012345678');
insert into member values('sonjh00', '손지혜', '5432', '01012123434');

create table movie(
movieNumber int auto_increment not null primary key,
movieName varchar(20) not null,
runningTime int not null,
genre varchar(20) not null,
screenDate date not null
);

insert into movie values(1002, '아이언맨2', 125, '액션', 20100429);
insert into movie values(1003, '아이언맨3', 129, '액션', 20130425);
insert into movie values(1004, '어벤져스1', 142, '액션', 20080420);
insert into movie values(1005, '어벤져스2', 141, '액션', 20100429);
insert into movie values(1006, '어벤져스3', 149, '액션', 20130425);
insert into movie values(1007, '어벤져스4', 181, '액션', 20130425);


create table reservation(
reservationNumber int auto_increment not null primary key,
id varchar(20) not null,
movieNumber int not null,
movieName varchar(20) not null,
screenDate date not null,
reservationDate date not null,
foreign key(id) references member(id),
foreign key(movieNumber) references movie(movieNumber)
);


commit;

select * from member;
select * from movie;
select * from reservation
