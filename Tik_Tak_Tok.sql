-- drop database tik_tak_tok;

create database tik_tak_tok;
use tik_tak_tok;
  
  create table game
(
    game_id int auto_increment
        primary key,
    Winner  varchar(100) null,
    constraint game_id_UNIQUE
        unique (game_id)
);

create table player
(
    username        varchar(100)         not null,
    player_id       int auto_increment
        primary key,
    online          tinyint(1) default 0 null,
    hashed_password varchar(50)          not null,
    wins            int        default 0 null,
    losses          int        default 0 null,
    Score           int        default 0 null,
    constraint username
        unique (username),
    constraint username_UNIQUE
        unique (username)
);

create table play
(
    step_number int         not null,
    steps       varchar(45) null,
    Game_id     int         not null,
    player_x    int         not null,
    player_o    int         not null,
    primary key (Game_id, player_x, player_o,step_number),
    constraint Game_id
        foreign key (Game_id) references game (game_id),
    constraint `player o`
        foreign key (player_o) references player (player_id),
    constraint `player x`
        foreign key (player_x) references player (player_id)
);

create index Game_id_idx
    on play (Game_id);

create index `player o_idx`
    on play (player_o);

create index `player x_idx`
    on play (player_x);


  
  select* from player;
  select* from play;
  select* from Game;
  
