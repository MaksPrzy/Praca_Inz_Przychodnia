package pl.przybylo.przychodnia.dto.uzytkownik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZarejestrowanoDto {

    private PacjentDetailViewDto uzytkownikRejestracja;
    private String token;

}
