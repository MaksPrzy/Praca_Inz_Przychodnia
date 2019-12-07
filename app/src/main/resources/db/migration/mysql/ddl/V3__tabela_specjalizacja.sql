create table specjalizacja (
  id bigint auto_increment,
  nazwa varchar(128) not null,
  full_text_search text,
  primary key(id),
  constraint specjalizacja_nazwa_uq unique(nazwa)
);
