package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacjentEditDto extends AbstractPacjentDto {

    private Long id;

    public PacjentEditDto(Long id, String imie, String nazwisko, String pesel,
                          LocalDate dataUrodzenia, String login, String haslo, AdresDto adres, KontaktDto kontakt) {
        super(imie, nazwisko, pesel, dataUrodzenia, login, haslo, adres, kontakt);
        this.id = id;
    }

}
