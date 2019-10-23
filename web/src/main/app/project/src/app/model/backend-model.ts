/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.16.538 on 2019-10-05 16:38:59.

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
    gabinetId: number;
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
}

export interface AbstractLekarzDto {
    imie: string;
    nazwisko: string;
    numer: string;
    specjalizacjaCollection: AbstractLekarzSpecjalizacjaDto[];
}

export interface AbstractLekarzSpecjalizacjaDto {
    id: number;
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
    nazwa: string;
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
}

export interface AdresDto {
    kodPocztowy: string;
    miejscowosc: string;
    ulica: string;
    numerDomu: string;
    numerLokalu: string;
}

export interface KontaktDto {
    email: string;
    telefonKomorkowy: string;
    telefonStacjonarny: string;
}

export interface PacjentDetailViewDto extends AbstractPacjentDto {
    id: number;
    numerKartoteki: string;
    adres: AdresDto;
    kontakt: KontaktDto;
}

export interface PacjentNewDto extends AbstractPacjentDto {
    adres: AdresDto;
    kontakt: KontaktDto;
}

export interface PacjentViewDto extends AbstractPacjentDto {
    adres: AdresDto;
    kontakt: KontaktDto;
    id: number;
}

export interface AbstractWizytaDto {
    pacjent: string;
    lekarz: string;
    harmonogramPozycja: string;
    gabinet: string;
}

export interface WizytaDetailViewDto extends AbstractWizytaDto {
    id: number;
}

export interface WizytaEditDto extends AbstractWizytaDto {
    id: number;
}

export interface WizytaNewDto extends AbstractWizytaDto {
}

export interface WizytaViewDto extends AbstractWizytaDto {
    id: number;
}
