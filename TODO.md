1. Rozszerzyc tabele pacjent o: login, haslo
- create table sql -> modyfikacja
- sample sql -> modyfikacja
- mappery -> modyfikacja

2. Usunac WizytaController. Przeniesc metody z WizytaController 
do PacjentController. Pozmieniac url, tak jak bylo robione miedzy
LekarzController a HarmonogramController.

3. Z ZaplanujWizyteDto usunac pole status - jest niepotrzebne.

4. Zrobic TokenService, z metoda isLoggedIn, która na razie 
zawsze zwraca true.

5. Zmiana gabinetId na gabinet (typu GabinetViewDto):
[ {
  "obowiazujeOd" : "2019-10-01T02:00:00",
  "obowiazujeDo" : "2020-10-01T02:00:00",
  "pozycjaCollection" : [ {
    "gabinetId" : 1,
    "dzienTygodnia" : 3,
    "godzinaOd" : "16:30:00",
    "godzinaDo" : "18:30:00",
    "interwalCzasowyWMinutach" : 30
  }, {
    "gabinetId" : 1,
    "dzienTygodnia" : 1,
    "godzinaOd" : "08:00:00",
    "godzinaDo" : "13:00:00",
    "interwalCzasowyWMinutach" : 30
  } ],
  "id" : 1,
  "specjalizacjaId" : 4
} ]
