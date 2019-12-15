create table pacjent (
  id bigint auto_increment,
  numer_kartoteki varchar(32) not null,
  pesel varchar(11) not null,
  imie varchar(128) not null,
  nazwisko varchar(128) not null,
  email varchar(512),
  haslo varchar (16) not null,
  telefon_komorkowy varchar(16),
  data_urodzenia date not null,
  kod_pocztowy varchar(6),
  miejscowosc varchar(128),
  ulica varchar(256),
  numer_domu varchar(8),
  full_text_search text,
  primary key(id),

  constraint pacjent_pesel_uq unique(pesel),
  constraint pacjent_numer_kartoteki_uq unique(numer_kartoteki)
);
