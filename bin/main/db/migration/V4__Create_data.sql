insert into users (user_id, email)
values (nextval('users_sequence'), 'anonymus@ttu.ee');
insert into users (user_id, email)
values (nextval('users_sequence'), 'anonymus2@ttu.ee');
insert into users (user_id, email)
values (nextval('users_sequence'), 'anonymus3@ttu.ee');

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