package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacjentEditDto extends AbstractPacjentDto {

    private Long id;

    public PacjentEditDto(Long id, String imie, String nazwisko, String pesel,
                          LocalDate dataUrodzenia, AdresDto adres, KontaktDto kontakt) {
        super(imie, nazwisko, pesel, dataUrodzenia, adres, kontakt);
        this.id = id;
    }

}
