package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacjentDetailViewDto extends AbstractPacjentDto {

    private Long id;
    private String numerKartoteki;
    private String haslo;

    public PacjentDetailViewDto(Long id, String numerKartoteki, String imie, String nazwisko, String pesel,
                                LocalDate dataUrodzenia, String email, String haslo, String telefonKomorkowy, AdresDto adres) {
        super(imie, nazwisko, pesel, dataUrodzenia, email, haslo, telefonKomorkowy, adres);
        this.id = id;
        this.numerKartoteki = numerKartoteki;
        this.haslo = haslo;
    }

}
