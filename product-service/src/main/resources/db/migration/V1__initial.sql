create sequence productId_seq start 1;
create sequence categoryId_seq start 1;
create sequence operationId_seq start 1;

create table category (
    id int,
    description varchar(40) not null,
    primary key (id)
);

create table product (
    id int,
    description varchar(40) not null,
    price numeric(15,4) not null,
    code varchar(40) not null,
    category_id int not null,
    insert timestamp not null,
    update timestamp,
    primary key (id),
    foreign key (category_id) references category (id)
);

create table operation (
    id int,
    type varchar(30) not null,
    qtde int not null,
    product_id int not null,
    date_move timestamp not null,
    primary key (id),
    foreign key(product_id) references product (id)
)