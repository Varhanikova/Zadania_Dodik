create table if not exists Login(
    username varchar(50) not null,
    password varchar(50) not null,
    premium VARCHAR(3),
    fee DOUBLE
);
create table if not exists Song(
    name VARCHAR(50) not null,
    autor VARCHAR(50) not null,
    fee DOUBLE
);
create table if not exists Ad(
    sponzor VARCHAR(50) not null,
    profit DOUBLE not null,
    probability DOUBLE not null,
    used INT=0
);
create table if not exists Playlist(
    nazov VARCHAR(20),

);