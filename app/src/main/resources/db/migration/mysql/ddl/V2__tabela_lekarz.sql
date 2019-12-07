create table lekarz (
  id bigint auto_increment,
  imie varchar(128) not null,
  nazwisko varchar(128) not null,
  numer_wykonywania_zawodu varchar(128) not null,
  full_text_search text,
  primary key(id),
  constraint lekarz_numer_wyk_zawodu_uq unique(numer_wykonywania_zawodu)
);
