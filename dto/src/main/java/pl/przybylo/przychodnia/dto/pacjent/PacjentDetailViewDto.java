package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PacjentDetailViewDto extends AbstractPacjentDto {

    private Long id;
    private String numerKartoteki;

    public PacjentDetailViewDto(Long id, String numerKartoteki, String imie, String nazwisko, String pesel,
                                LocalDate dataUrodzenia, String login, AdresDto adres, KontaktDto kontakt) {
        super(imie, nazwisko, pesel, dataUrodzenia, login, adres, kontakt);
        this.id = id;
        this.numerKartoteki = numerKartoteki;
    }

}
