create table if not exists Login(
    username varchar(50) not null,
    password varchar(50) not null,
    premium VARCHAR(1),
    fee DOUBLE,
    primary key(username)
);
create table if not exists Song(
    id INTEGER primary key,
    autor VARCHAR(50) not null,
    name VARCHAR(50) not null,
    fee FLOAT not null
);
create table if not exists Ad(
    sponzor VARCHAR(50) not null,
    profit DOUBLE not null,
    probability DOUBLE not null,
    used INT,
    primary key(sponzor)
);
create table if not exists Playlist(
    id INT primary key,
    name VARCHAR(20) not null,
    username VARCHAR(50) not null references Login(username)
);
create table if not exists PlaylistSong(
    id INTEGER primary key,
    id_playlist INT not null references Playlist(id),
    id_song INT not null references Song(id)
);