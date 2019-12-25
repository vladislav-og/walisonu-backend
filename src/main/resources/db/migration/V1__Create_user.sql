create sequence users_sequence start with 1;

CREATE TABLE users
(
  user_id BIGINT NOT NULL PRIMARY KEY,
  email   VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255),
  role VARCHAR(255)
);