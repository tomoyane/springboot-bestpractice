USE test;

DROP TABLE IF EXISTS user_keys;
DROP TABLE IF EXISTS signature_keys;
DROP TABLE IF EXISTS infos;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS items;

-- users table
CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  uuid varchar(128) NOT NULL,
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
  token_type varchar(128) NOT NULL,
  token varchar(512) NOT NULL,
  refresh_token varchar(512) NOT NULL,
  user_id int(11) NOT NULL,
  expires_at datetime(6),
  created_at datetime(6),
  PRIMARY KEY (id),
  UNIQUE (token),
  FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- signature_keys table
CREATE TABLE signature_keys (
  id int(11) NOT NULL AUTO_INCREMENT,
  signature_key varchar(128) NOT NULL,
  user_id int(11) NOT NULL,
  created_at datetime(6),
  PRIMARY KEY (id),
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

-- items table
CREATE TABLE items (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,
  category varchar(128) NOT NULL,
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

-- items test data
INSERT INTO items(id, name, category, created_at)
VALUES (1, 'test_title01', 'Im fine', '1970-01-01 00:00:01');

INSERT INTO items(id, name, category, created_at)
VALUES (2, 'hoge', 'hoge', '1970-01-01 00:00:01');
