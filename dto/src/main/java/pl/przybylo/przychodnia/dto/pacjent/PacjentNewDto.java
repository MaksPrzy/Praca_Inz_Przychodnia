package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacjentNewDto extends AbstractPacjentDto {

    public PacjentNewDto(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, String email, String haslo, String telefonKomorkowy, AdresDto adres) {
        super(imie, nazwisko, pesel, dataUrodzenia, email, haslo, telefonKomorkowy, adres);
    }

}
