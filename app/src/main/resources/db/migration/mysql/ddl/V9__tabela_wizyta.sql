create table wizyta (
  id bigint auto_increment,
  pacjent_id bigint not null,
  lekarz_id bigint not null default 0,
  specjalizacja_id bigint not null,
  gabinet_id bigint not null,
  data_wizyty_od datetime not null,
  data_wizyty_do datetime not null,
  status varchar(128) not null,
  rodzaj varchar(12) not null,
  full_text_search text,

  primary key(id),
  constraint wizyta_pacjent_id_fk foreign key (pacjent_id) references pacjent (id),
  constraint wizyta_lekarz_fk foreign key (lekarz_id) references lekarz (id),
  constraint wizyta_specjalizacja_fk foreign key (lekarz_id) references lekarz (id),
  constraint wizyta_gabinet_id_fk foreign key (gabinet_id) references gabinet (id)
);
