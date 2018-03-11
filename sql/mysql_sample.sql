USE test;

DROP TABLE IF EXISTS user_keys;
DROP TABLE IF EXISTS infos;
DROP TABLE IF EXISTS users;

-- users table
CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(128) NOT NULL,
  email varchar(128) NOT NULL,
  password varchar(128) NOT NULL,
  created_at datetime(6),
  PRIMARY KEY (id),
  UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- user_keys table
CREATE TABLE user_keys (
  id int(11) NOT NULL AUTO_INCREMENT,
  token varchar(128) NOT NULL,
  refresh_token varchar(128) NOT NULL,
  user_id int(11) NOT NULL,
  created_at datetime(6),
  PRIMARY KEY (id),
  UNIQUE (token),
  FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- infos table
CREATE TABLE infos (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(128) NOT NULL,
  description varchar(128) NOT NULL,
  created_at datetime(6),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- infos test data
INSERT INTO infos(id, title, description, created_at)
VALUES (1, 'test_title01', 'Im fine', '1970-01-01 00:00:01');

INSERT INTO infos(id, title, description, created_at)
VALUES (2, 'test_title02', 'Spring boot!!!', '1970-01-01 00:00:02');

INSERT INTO infos(id, title, description, created_at)
VALUES (3, 'test_title03', 'important test', '1970-01-01 00:02:01');

INSERT INTO infos(id, title, description, created_at)
VALUES (4, 'test_title04', 'Angular5!!', '1970-01-01 00:00:00');

INSERT INTO infos(id, title, description, created_at)
VALUES (5, 'test_title05', 'Django!!', '1969-01-01 00:00:01');

INSERT INTO infos(id, title, description, created_at)
VALUES (6, 'test_title06', 'sleep', '1968-01-01 00:00:01');

INSERT INTO infos(id, title, description, created_at)
VALUES (7, 'test_title07', 'xxxxxxx', '1977-01-01 00:00:01');