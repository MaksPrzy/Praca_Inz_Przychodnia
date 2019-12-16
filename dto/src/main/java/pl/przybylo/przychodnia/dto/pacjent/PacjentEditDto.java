package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacjentEditDto extends AbstractPacjentDto {

    private Long id;
    private String haslo;

    public PacjentEditDto(Long id, String imie, String nazwisko, String pesel,
                          LocalDate dataUrodzenia, String email, String haslo, String telefonKomorkowy, AdresDto adres) {
        super(imie, nazwisko, pesel, dataUrodzenia, email, haslo, telefonKomorkowy, adres);
        this.id = id;
        this.haslo = haslo;
    }

}
