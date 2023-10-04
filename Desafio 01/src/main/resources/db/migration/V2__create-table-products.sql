create table products(
    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(400),
    price float not null,

    primary key(id)
);