create table uzytkownik (
  id bigint auto_increment,
  login varchar(128) not null,
  imie varchar(128) not null,
  nazwisko varchar(128) not null,
  full_text_search text,

  primary key(id),
  constraint uzytkownik_login_uq unique(login)
);
