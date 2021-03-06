create table if not exists BASEBALL_GAME
(
    id bigint auto_increment not null,
    primary key (id)
);

create table if not exists BASEBALL_NUMBER
(
    entity_id    bigint not null,
    number_index bigint not null,
    number       bigint not null
);

create table if not exists GUESS
(
    id               bigint auto_increment not null,
    baseball_game_id bigint                not null,
    primary key (id)
);