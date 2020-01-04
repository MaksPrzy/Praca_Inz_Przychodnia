package pl.przybylo.przychodnia.dto.pacjent;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.przybylo.przychodnia.dto.pacjent.AbstractPacjentDto;
import pl.przybylo.przychodnia.dto.pacjent.AdresDto;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PacjentRejestracjaDto extends AbstractPacjentDto {

    public PacjentRejestracjaDto(String imie, String nazwisko, String pesel, LocalDate dataUrodzenia, String email, String haslo, String telefonKomorkowy, AdresDto adres) {
        super(imie, nazwisko, pesel, dataUrodzenia, email, haslo, telefonKomorkowy, adres);
    }

}
