package pl.przybylo.przychodnia.dto.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HarmonogramZaplanowanaWizytaRequestDto {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long lekarzId;
    private Long specjalizacjaId;

}
