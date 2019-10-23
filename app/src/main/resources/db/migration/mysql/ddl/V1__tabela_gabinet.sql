create table gabinet (
  id bigint auto_increment,
  nazwa varchar(128) not null,
  opis text,
  pietro int not null default 0,
  primary key(id),
  constraint gabinet_nazwa_uq unique(nazwa)
);
