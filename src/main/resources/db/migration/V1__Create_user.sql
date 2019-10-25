create sequence users_sequence start with 1;

CREATE TABLE users
(
  user_id BIGINT PRIMARY KEY,
  email   VARCHAR(255) NOT NULL
);