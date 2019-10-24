create sequence users_sequence start with 1;
create sequence word_sequence start with 1;
create sequence synonym_sequence start with 1;
CREATE TABLE users
(
  user_id BIGINT PRIMARY KEY,
  email   VARCHAR(255) NOT NULL
);

CREATE TABLE words
(
  word_id  BIGINT PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  user_id  BIGINT,
  isActive BOOLEAN,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE synonyms
(
  synonym_id  BIGINT PRIMARY KEY,
  word_id  BIGINT,
  synonym     VARCHAR(255) NOT NULL,
  user_id  BIGINT,
  isActive BOOLEAN,
  FOREIGN KEY (word_id) REFERENCES words (word_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);