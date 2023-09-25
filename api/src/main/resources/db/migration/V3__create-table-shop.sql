create table shop(
    user_id_fk bigint not null,
    product_id_fk bigint not null,

    primary key(user_id_fk, product_id_fk),
    foreign key(user_id_fk) references users(id),
    foreign key(product_id_fk) references products(id)
);