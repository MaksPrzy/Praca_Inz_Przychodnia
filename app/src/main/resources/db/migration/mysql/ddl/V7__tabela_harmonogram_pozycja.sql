create table harmonogram_pozycja (
  id bigint auto_increment,
  harmonogram_id bigint not null,
  gabinet_id bigint not null,
  dzien_tygodnia int not null,
  godzina_od time not null,
  godzina_do time not null,
  interwal_czasowy int not null,

  primary key(id),
  constraint harmonogram_pozycja_harmonogram_id_fk foreign key (harmonogram_id) references harmonogram (id),
  constraint harmonogram_pozycja_gabinet_id_fk foreign key (gabinet_id) references gabinet (id)
);
