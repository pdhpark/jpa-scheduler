create table schedule
(
    id       bigint       primary key auto_increment,
    username varchar(100) not null,
    title varchar(100) not null,
    contents varchar(500) not null

);