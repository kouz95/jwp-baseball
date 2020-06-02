create table if not exists BASEBALL_GAME (
    id bigint auto_increment not null,
    primary key (id)
);

create table if not exists BASEBALL_NUMBER (
  baseball_game bigint not null,
  number bigint not null
);