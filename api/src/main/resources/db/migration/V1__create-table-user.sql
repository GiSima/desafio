create table users(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null unique,
    balance float not null,

    primary key(id)
);