create table harmonogram (
  id bigint auto_increment,
  lekarz_id bigint not null,
  specjalizacja_id bigint not null,
  obowiazuje_od date not null,
  obowiazuje_do date not null,
  aktywny boolean not null default false,
  full_text_search text,
  primary key(id),

  constraint harmonogram_lekarz_id_fk foreign key (lekarz_id) references lekarz (id)
);
