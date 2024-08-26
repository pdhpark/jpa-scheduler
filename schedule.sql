create table schedule
(
    id       bigint       primary key auto_increment,
    username varchar(100) not null,
    title varchar(100) not null,
    contents varchar(500) not null

);

create table comments
(
    id       bigint       primary key auto_increment,
    username varchar(100) not null,
    contents varchar(500) not null

);

create table user
(
    id       bigint       primary key auto_increment,
    username varchar(100) not null,
    email varchar(500) not null

);

create table register
(
    id bigint primary key auto_increment
);

alter table schedule change username_id user_id bigint;