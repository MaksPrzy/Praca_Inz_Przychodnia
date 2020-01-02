package pl.przybylo.przychodnia.dto.uzytkownik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZarejestrujDto {

    private String pesel;
    private String imie;
    private String nazwisko;
    private String email;
    private String haslo;
    private String telefon;
    private Date dataUrodzenia;
    private String kodPoczowy;
    private String miejscowosc;
    private String ulica;
    private String numerDomu;

}
