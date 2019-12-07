/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.16.538 on 2019-12-07 16:39:00.

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
    adres: AdresDto;
    kontakt: KontaktDto;
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
}

export interface PacjentEditDto extends AbstractPacjentDto {
    id: number;
}

export interface PacjentNewDto extends AbstractPacjentDto {
}

export interface AbstractWizytaDto {
    pacjentId: number;
    lekarzId: number;
    specjalizacjaId: number;
    gabinetId: number;
    dataWizytyOd: Date;
    dataWizytyDo: Date;
    status: string;
    rodzaj: string;
}

export interface WizytaEditDto extends AbstractWizytaDto {
    id: number;
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
