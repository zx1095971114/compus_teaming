create database Vteam;

use Vteam;

create table vteam_user
(
    username varchar(20) primary key,
    password varchar(20),
    status   varchar(10)
);

create table user_info
(
    username     varchar(20) primary key,
    email        varchar(70),
    name         varchar(20),
    phoneNumber  varchar(11),
    school       varchar(20),
    grade        varchar(20),
    sex          varchar(10),
    avatarPath   varchar(270),
    registerTime varchar(30)
)
