CREATE KEYSPACE IF NOT EXISTS spring_boot
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2};

CREATE TABLE IF NOT EXISTS spring_boot.infos
(
    id          TEXT PRIMARY KEY,
    title       TEXT,
    description TEXT
);

CREATE TABLE IF NOT EXISTS spring_boot.users
(
    id       TEXT PRIMARY KEY,
    username TEXT,
    email    TEXT,
    password TEXT
);
