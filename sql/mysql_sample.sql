USE test;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS infos;

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

-- infos table
CREATE TABLE infos (
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(128) NOT NULL,
  description varchar(128) NOT NULL,
  created_at datetime(6),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- users test data
INSERT INTO users(id, username, email, password, created_at)
VALUES (1, 'hoge01', 'aaa@gmail.com', 'test01', '1970-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (2, 'hoge02', 'aaa+2@gmail.com', 'test02', '1971-01-02 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (3, 'hoge03', 'aaa+3@gmail.com', 'test03', '1972-01-03 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (4, 'hoge04', 'aaa+4@gmail.com', 'test04', '1973-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (5, 'hoge05', 'aaa+5@gmail.com', 'test05', '1972-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (6, 'hoge06', 'aaa+6@gmail.com', 'test06', '1969-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (7, 'hoge07', 'aaa+7@gmail.com', 'test07', '1933-01-01 00:00:01');

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