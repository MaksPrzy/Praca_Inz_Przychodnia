create table lekarz_specjalizacja (
  id bigint auto_increment,
  lekarz_id bigint not null,
  specjalizacja_id bigint not null,
  rok_uzyskania_dyplomu_ze_specjalizacji int not null,
  primary key(id),
  constraint lekarz_specjalizacja_lekarz_id_fk foreign key (lekarz_id) references lekarz (id),
  constraint lekarz_specjalizacja_specjalizacja_id_fk foreign key (specjalizacja_id) references specjalizacja (id),
  constraint lekarz_specjalizacja_lekarz_specjalizacja_uq unique(lekarz_id, specjalizacja_id)
)
