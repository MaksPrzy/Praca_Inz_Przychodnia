package pl.przybylo.przychodnia.dto.wizyta;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.SpecjalizacjaViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public abstract class AbstractWizytaDto {

    private Long id;
    protected PacjentDetailViewDto pacjent;
    protected LekarzDetailViewDto lekarz;
    protected SpecjalizacjaViewDto specjalizacja;
    protected GabinetViewDto gabinet;
    protected LocalDateTime dataWizytyOd;
    protected LocalDateTime dataWizytyDo;
    protected String status;
    protected String rodzaj;

}
