create sequence synonym_sequence start with 1;

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