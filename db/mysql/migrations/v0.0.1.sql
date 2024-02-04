DROP TABLE IF EXISTS infos;
DROP TABLE IF EXISTS users;

-- users table
CREATE TABLE users (
  id varchar(64) NOT NULL,
  username varchar(128) NOT NULL,
  email varchar(128) NOT NULL,
  password varchar(128) NOT NULL,
  created_at datetime(6),
  PRIMARY KEY (id),
  UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- infos table
CREATE TABLE infos (
  id varchar(64) NOT NULL,
  title varchar(128) NOT NULL,
  description varchar(128) NOT NULL,
  created_at datetime(6),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
