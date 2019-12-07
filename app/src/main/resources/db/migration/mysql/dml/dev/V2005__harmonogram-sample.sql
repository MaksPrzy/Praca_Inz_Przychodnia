-- Lucja Mila harmonogram
insert into harmonogram(lekarz_id, specjalizacja_id, obowiazuje_od, obowiazuje_do, aktywny)
values(2, 4, '2019-10-01 07:00:00', '2020-10-01 20:00:00', true);

insert into lekarz_harmonogram(lekarz_id, harmonogram_id)
values(2, 1);

-- przyjmuje w poniedzialki
insert into harmonogram_pozycja(harmonogram_id, gabinet_id, dzien_tygodnia, godzina_od, godzina_do, interwal_czasowy)
values(1, 1, 1, '07:00:00', '12:00:00', 30);

-- przyjmuje w srody
insert into harmonogram_pozycja(harmonogram_id, gabinet_id, dzien_tygodnia, godzina_od, godzina_do, interwal_czasowy)
values(1, 1, 3, '15:30:00', '17:30:00', 30);



-- Mateusz Rogal harmonogram
insert into harmonogram(lekarz_id, specjalizacja_id, obowiazuje_od, obowiazuje_do, aktywny)
values(4, 3, '2019-11-01 07:00:00', '2020-05-01 20:00:00', true);

insert into lekarz_harmonogram(lekarz_id, harmonogram_id)
values(4, 2);

-- przyjmuje w wtorki
insert into harmonogram_pozycja(harmonogram_id, gabinet_id, dzien_tygodnia, godzina_od, godzina_do, interwal_czasowy)
values(2, 3, 2, '09:00:00', '13:00:00', 20);

-- przyjmuje w piatki
insert into harmonogram_pozycja(harmonogram_id, gabinet_id, dzien_tygodnia, godzina_od, godzina_do, interwal_czasowy)
values(2, 4, 5, '09:00:00', '17:20:00', 20);
