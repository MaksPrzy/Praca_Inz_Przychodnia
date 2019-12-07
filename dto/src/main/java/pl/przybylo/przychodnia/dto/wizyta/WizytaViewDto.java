package pl.przybylo.przychodnia.dto.wizyta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.SpecjalizacjaViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WizytaViewDto {

    private Long id;
    private PacjentDetailViewDto pacjent;
    private LekarzDetailViewDto lekarz;
    private SpecjalizacjaViewDto specjalizacja;
    private GabinetViewDto gabinet;
    private LocalDateTime dataWizytyOd;
    private LocalDateTime dataWizytyDo;
    private String status;
    private String rodzaj;

}
