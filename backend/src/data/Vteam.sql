create database Vteam;

use Vteam;

create table vteam_user
(
    username varchar(20) primary key, # 账号
    password varchar(20),             # 密码
    status   varchar(10)              # 权限
);

create table user_info
(
    username     varchar(20) primary key, #账号
    email        varchar(70),
    name         varchar(20),
    phoneNumber  varchar(11),
    school       varchar(20),
    grade        varchar(20),
    sex          varchar(10),
    avatarPath   varchar(270),
    registerTime datetime
);

create table settings
(
    primary key (classes, tags),
    classes varchar(20),
    tags  varchar(20)
);


create table recruit_info
(
    rid         varchar(70) primary key,
    rtitle       varchar(70),
    description varchar(500),
    subscribe   integer,
    rclass      varchar(20),
    rtags       varchar(200),
    content     varchar(700),
    creator     varchar(20),
    img         varchar(70),
    startTime   datetime,
    endTime     datetime,
    tid         varchar(70),
    byTeacher   integer,
    isDestroy   integer
);

create table vteam_info
(
    tid          varchar(70) primary key,
    maxMates     integer,
    currentMates integer,
    teamMates    varchar(500),
    successTime  datetime,
    isSuccess    integer
);

create table message
(
    mid      varchar(70) primary key,
    status   integer,
    username varchar(20),
    mtitle   varchar(70),
    message  varchar(200),
    mtime    datetime
);

create table history
(
    hid      varchar(70) primary key,
    username varchar(20),
    htitle   varchar(70),
    history  varchar(200),
    htime    datetime
)
