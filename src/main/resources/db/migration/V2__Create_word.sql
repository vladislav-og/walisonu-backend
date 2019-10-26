create sequence word_sequence start with 1;

CREATE TABLE words
(
  word_id  BIGINT NOT NULL PRIMARY KEY,
  name     VARCHAR(255) NOT NULL UNIQUE ,
  user_id  BIGINT,
  isActive BOOLEAN,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);