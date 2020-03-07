/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.16.538 on 2020-03-07 10:08:34.

export interface AbstractGabinetDto {
    nazwa: string;
    opis: string;
    pietro: number;
}

export interface GabinetEditDto extends AbstractGabinetDto {
    id: number;
}

export interface GabinetNewDto extends AbstractGabinetDto {
}

export interface GabinetViewDto extends AbstractGabinetDto {
    id: number;
}

export interface AbstractHarmonogramDto {
    obowiazujeOd: Date;
    obowiazujeDo: Date;
    pozycjaCollection: AbstractHarmonogramPozycjaDto[];
}

export interface AbstractHarmonogramPozycjaDto {
    gabinet: GabinetViewDto;
    dzienTygodnia: number;
    godzinaOd: Date;
    godzinaDo: Date;
    interwalCzasowyWMinutach: number;
}

export interface HarmonogramEditDto extends AbstractHarmonogramDto {
    id: number;
    lekarzId: number;
    shouldCheckDatesImpose: boolean;
}

export interface HarmonogramNewDto extends AbstractHarmonogramDto {
    lekarzId: number;
    shouldCheckDatesImpose: boolean;
}

export interface HarmonogramPozycjaEditDto extends AbstractHarmonogramPozycjaDto {
}

export interface HarmonogramPozycjaNewDto extends AbstractHarmonogramPozycjaDto {
}

export interface HarmonogramPozycjaViewDto extends AbstractHarmonogramPozycjaDto {
}

export interface HarmonogramViewDto extends AbstractHarmonogramDto {
    id: number;
    specjalizacjaId: number;
}

export interface AbstractLekarzDto {
    imie: string;
    nazwisko: string;
    numer: string;
    specjalizacjaCollection: AbstractLekarzSpecjalizacjaDto[];
}

export interface AbstractLekarzSpecjalizacjaDto {
    id: number;
    nazwa: string;
    rokUzyskaniaDyplomuZeSpecjalizacji: number;
}

export interface LekarzDetailViewDto extends AbstractLekarzDto {
    id: number;
}

export interface LekarzEditDto extends AbstractLekarzDto {
    id: number;
}

export interface LekarzNewDto extends AbstractLekarzDto {
}

export interface LekarzSpecjalizacjaEditDto extends AbstractLekarzSpecjalizacjaDto {
}

export interface LekarzSpecjalizacjaNewDto extends AbstractLekarzSpecjalizacjaDto {
}

export interface LekarzSpecjalizacjaViewDto extends AbstractLekarzSpecjalizacjaDto {
}

export interface SpecjalizacjaViewDto {
    id: number;
    nazwa: string;
}

export interface AbstractPacjentDto {
    imie: string;
    nazwisko: string;
    pesel: string;
    dataUrodzenia: Date;
    email: string;
    haslo: string;
    telefonKomorkowy: string;
    adres: AdresDto;
}

export interface AdresDto {
    kodPocztowy: string;
    miejscowosc: string;
    ulica: string;
    numerDomu: string;
}

export interface PacjentDetailViewDto extends AbstractPacjentDto {
    id: number;
    numerKartoteki: string;
}

export interface PacjentEditDto extends AbstractPacjentDto {
    id: number;
}

export interface PacjentRejestracjaDto extends AbstractPacjentDto {
}

export interface UzytkownikDto extends UserDetails {
    uzytkownik: PacjentDetailViewDto;
}

export interface UzytkownikRejestracjaDto extends UserDetails {
    uzytkownikRejestracja: PacjentDetailViewDto;
    telefonKomorkowy: string;
    numerDomu: AdresDto;
    miejscowosc: AdresDto;
    ulica: AdresDto;
    kodPocztowy: AdresDto;
    imie: string;
    nazwisko: string;
    pesel: string;
    dataUrodzenia: Date;
}

export interface ZalogowanoDto {
    uzytkownik: PacjentDetailViewDto;
    token: string;
}

export interface ZalogujDto {
    username: string;
    password: string;
}

export interface ZarejestrowanoDto {
    uzytkownikRejestracja: PacjentDetailViewDto;
    token: string;
}

export interface AbstractWizytaDto {
    pacjentId: number;
    lekarzId: number;
    specjalizacjaId: number;
    gabinet: number;
    dataWizytyOd: Date;
    dataWizytyDo: Date;
    rodzaj: string;
}

export interface HarmonogramZaplanowanaWizytaDto {
    dayIndex: number;
    minuteFrom: number;
    minuteTo: number;
}

export interface HarmonogramZaplanowanaWizytaRequestDto {
    dateFrom: Date;
    dateTo: Date;
    lekarzId: number;
    specjalizacjaId: number;
}

export interface WizytaEditDto extends AbstractWizytaDto {
    id: number;
    status: string;
}

export interface WizytaViewDto {
    id: number;
    pacjent: PacjentDetailViewDto;
    lekarz: LekarzDetailViewDto;
    specjalizacja: SpecjalizacjaViewDto;
    gabinet: GabinetViewDto;
    dataWizytyOd: Date;
    dataWizytyDo: Date;
    status: string;
    rodzaj: string;
}

export interface ZakonczWizyteDto {
    wizytaId: number;
    kodIcd10: string;
    uwagi: string;
}

export interface ZaplanujWizyteDto extends AbstractWizytaDto {
}

export interface GrantedAuthority extends Serializable {
    authority: string;
}

export interface UserDetails extends Serializable {
    password: string;
    enabled: boolean;
    username: string;
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;
    authorities: GrantedAuthority[];
}

export interface Serializable {
}
