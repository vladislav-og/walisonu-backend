create sequence synonym_sequence start with 1;

CREATE TABLE synonyms
(
  synonym_id BIGINT NOT NULL PRIMARY KEY,
  word_id    BIGINT NOT NULL,
  synonym    VARCHAR(255) NOT NULL,
  user_id    BIGINT,
  isActive   BOOLEAN,
  FOREIGN KEY (word_id) REFERENCES words (word_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);