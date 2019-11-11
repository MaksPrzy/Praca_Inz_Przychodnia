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

    protected Long pacjentId;
    protected Long lekarzId;
    protected Long specjalizacjaId;
    protected Long gabinetId;
    protected LocalDateTime dataWizytyOd;
    protected LocalDateTime dataWizytyDo;
    protected String status;
    protected String rodzaj;

}
