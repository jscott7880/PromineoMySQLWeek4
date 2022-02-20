create database if not exists cars;

use cars;

drop table if exists Cars;

create table Cars (
	id int(10) not null auto_increment,
    name varchar(50) not null,
    price varchar(7) not null,
    primary key(id)
);