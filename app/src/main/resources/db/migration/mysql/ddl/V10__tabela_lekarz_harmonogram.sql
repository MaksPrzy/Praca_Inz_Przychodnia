create table lekarz_harmonogram (
  id bigint auto_increment,
  lekarz_id bigint not null,
  harmonogram_id bigint not null,
  primary key(id),
  constraint lekarz_harmonogram_lekarz_id_fk foreign key (lekarz_id) references lekarz (id),
  constraint lekarz_harmonogram_harmonogram_id_fk foreign key (harmonogram_id) references harmonogram (id),
  constraint lekarz_harmonogram_lekarz_harmonogram_uq unique(lekarz_id, harmonogram_id)
)
