insert into users (user_id, email, password, role)
values (nextval('users_sequence'), 'anonymus@ttu.ee','$2a$10$5dQOR6xbgqPjXPp9jQjew.YXGwXATj.KZQv0GlNhAzbf1vl4IaSCG','ADMIN');
--Password: "admin" --
insert into users (user_id, email, password, role)
values (nextval('users_sequence'), 'anonymus2@ttu.ee','$2a$10$OuwaDD2ibfiL7nE.j.iveOHT6VvUjm3PG8ITpd1resbdQ1rPyhi7q','USER');
--Password: "user" --
insert into users (user_id, email, password, role)
values (nextval('users_sequence'), 'anonymus3@ttu.ee','$2a$10$OuwaDD2ibfiL7nE.j.iveOHT6VvUjm3PG8ITpd1resbdQ1rPyhi7q','USER');
--Password: "user" --

insert into words (word_id, name, user_id, isactive)
values (nextval('word_sequence'), 'Tüdruk', 1, TRUE);
insert into words (word_id, name, user_id, isactive)
values (nextval('word_sequence'), 'Poiss', 1, TRUE);
insert into words (word_id, name, user_id, isactive)
values (nextval('word_sequence'), 'Alkohol', 1, TRUE);

insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 1, 'Eit', 1, TRUE);
insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 1, 'Baby', 1, TRUE);
insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 1, 'Plika', 1, TRUE);

insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 2, 'Kutt', 1, TRUE);
insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 2, 'Lõngus', 1, TRUE);

insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 3, 'Samakas', 1, TRUE);
insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 3, 'Kärakas', 1, TRUE);
insert into synonyms (synonym_id, word_id, synonym, user_id, isactive)
values (nextval('synonym_sequence'), 3, 'Peedikas', 1, TRUE);