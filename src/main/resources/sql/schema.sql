drop table if exists auth cascade;
drop table if exists users;
create table users
(
    id        bigserial not null
        constraint users_pk
            primary key,
    login     varchar   not null,
    password  varchar,
    firstname varchar,
    lastname  varchar   not null,
    phone     varchar
);

alter table users
    owner to qsymond;

drop index if exists users_id_uindex;
create unique index users_id_uindex
    on users (id);

drop index if exists users_login_uindex;
create unique index users_login_uindex
    on users (login);
create table auth
(
    user_id bigint
        constraint auth_users_fk
            references users
            on update cascade on delete cascade,
    type varchar,
    address varchar,
    time varchar
);