create table pacjent (
  id bigint auto_increment,
  numer_kartoteki varchar(32) not null,
  pesel varchar(11) not null,
  imie varchar(128) not null,
  nazwisko varchar(128) not null,
  login varchar (128) not null,
  haslo varchar (512) not null,
  data_urodzenia date not null,
  kod_pocztowy varchar(6),
  miejscowosc varchar(128),
  ulica varchar(256),
  numer_domu varchar(8),
  numer_lokalu varchar(8),
  email varchar(512),
  telefon_komorkowy varchar(16),
  telefon_stacjonarny varchar(16),
  full_text_search text,
  primary key(id),

  constraint pacjent_pesel_uq unique(pesel),
  constraint pacjent_numer_kartoteki_uq unique(numer_kartoteki),
  constraint pacjent_login_uq unique(login)
);
