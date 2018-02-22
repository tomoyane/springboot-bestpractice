USE test;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS infos;

-- users table
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- infos table
CREATE TABLE `infos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `description` varchar(128) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- users test data
INSERT INTO users(id, username, email, password, created_at)
VALUES (1, 'toyane01', 'tohito0430@gmail.com', 'test01', '1970-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (2, 'toyane02', 'tohito0430+2@gmail.com', 'test02', '1971-01-02 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (3, 'toyane03', 'tohito0430+3@gmail.com', 'test03', '1972-01-03 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (4, 'toyane04', 'tohito0430+4@gmail.com', 'test04', '1973-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (5, 'toyane05', 'tohito0430+5@gmail.com', 'test05', '1972-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (6, 'toyane06', 'tohito0430+6@gmail.com', 'test06', '1969-01-01 00:00:01');

INSERT INTO users(id, username, email, password, created_at)
VALUES (7, 'toyane07', 'tohito0430+7@gmail.com', 'test07', '1933-01-01 00:00:01');

-- infos test data
INSERT INTO infos(id, title, description, created_at)
VALUES (1, 'test_title01', 'Im fine', '1970-01-01 00:00:01');

INSERT INTO users(id, username, email, password)
VALUES (2, 'test_title02', 'Spring boot!!!', '1970-01-01 00:00:02');

INSERT INTO users(id, username, email, password)
VALUES (1, 'test_title03', 'important test', '1970-01-01 00:02:01');

INSERT INTO users(id, username, email, password)
VALUES (1, 'test_title04', 'Angular5!!', '1970-01-01 00:00:00');

INSERT INTO users(id, username, email, password)
VALUES (1, 'test_title05', 'Django!!', '1969-01-01 00:00:01');

INSERT INTO users(id, username, email, password)
VALUES (1, 'test_title06', 'sleep', '1968-01-01 00:00:01');

INSERT INTO users(id, username, email, password)
VALUES (1, 'test_title07', 'xxxxxxx', '1977-01-01 00:00:01');