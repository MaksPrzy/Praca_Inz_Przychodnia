package pl.przybylo.przychodnia.dto.pacjent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public abstract class AbstractPacjentDto {

    protected String imie;
    protected String nazwisko;
    protected String pesel;
    protected LocalDate dataUrodzenia;
    protected String login;
    protected String haslo;
    protected AdresDto adres;
    protected KontaktDto kontakt;

}
