create table dishes
(
    id_dish int auto_increment
        constraint `PRIMARY`
        primary key,
    name    varchar(50)    not null,
    cost    decimal(20, 2) not null
);

create table users
(
    iduser   int auto_increment
        constraint `PRIMARY`
        primary key,
    email    varchar(50) not null,
    password varchar(50) not null,
    role     varchar(10) not null,
    name     varchar(20) null,
    phone    varchar(15) null,
    surname  varchar(20) null
);

create table orders
(
    id_order bigint auto_increment
        constraint `PRIMARY`
        primary key,
    id_user  int       not null,
    date     timestamp not null,
    status   text      null,
    sum      decimal   null,
    constraint orders_users_id_user_fk
        foreign key (id_user) references users (iduser)
);

create table ordered_dishes
(
    id_dish  int auto_increment
        constraint `PRIMARY`
        primary key,
    id_order bigint               null,
    is_ready tinyint(1) default 0 not null,
    constraint ordered_dishes_dishes_id_dish_fk
        foreign key (id_dish) references dishes (id_dish),
    constraint ordered_dishes_orders_id_order_fk
        foreign key (id_order) references orders (id_order)
);


