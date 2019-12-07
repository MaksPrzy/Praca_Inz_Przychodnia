package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacjentNewDto extends AbstractPacjentDto {

    public PacjentNewDto(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, AdresDto adres, KontaktDto kontakt) {
        super(imie, nazwisko, pesel, dataUrodzenia, adres, kontakt);
    }

}
