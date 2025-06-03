create table if not exists anonymous_message
(
    id           bigint auto_increment
        primary key,
    from_user_id bigint                               null,
    to_user_id   bigint                               not null,
    content      text                                 not null,
    timestamp    datetime   default CURRENT_TIMESTAMP null,
    is_read      tinyint(1) default 0                 null,
    reply_to_id  bigint                               null
);

create table if not exists chat_message
(
    id           bigint auto_increment
        primary key,
    from_user_id bigint                               not null,
    to_user_id   bigint                               not null,
    content      text                                 not null,
    type         varchar(20)                          not null,
    is_anonymous tinyint(1) default 0                 null,
    timestamp    datetime   default CURRENT_TIMESTAMP null
);

create table if not exists wx_user
(
    id        bigint auto_increment
        primary key,
    nickname  varchar(50)  not null,
    avatar    varchar(255) null,
    star_sign varchar(20)  null,
    birthday  date         null,
    age       int          null,
    height    int          null,
    weight    int          null,
    attribute varchar(10)  null,
    location  varchar(255) null,
    latitude  double       null,
    longitude double       null,
    password  varchar(100) null
);

