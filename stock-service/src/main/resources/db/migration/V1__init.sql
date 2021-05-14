create sequence stockId_seq start 1;

create table stock (
    id int,
    code varchar(40) not null,
    date_mov TIMESTAMP not null,
    exit int,
    entrance int,
    balance int,
    primary key (id)
);