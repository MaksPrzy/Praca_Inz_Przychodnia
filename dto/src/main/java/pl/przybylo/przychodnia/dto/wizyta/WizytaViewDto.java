package pl.przybylo.przychodnia.dto.wizyta;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.SpecjalizacjaViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class WizytaViewDto extends AbstractWizytaDto {

    public WizytaViewDto(Long id, PacjentDetailViewDto pacjent, LekarzDetailViewDto lekarz,
                         SpecjalizacjaViewDto specjalizacja, GabinetViewDto gabinet,
                         LocalDateTime dataWizytyOd, LocalDateTime dataWizytyDo,
                         String status, String rodzaj) {
        super(id, pacjent, lekarz, specjalizacja, gabinet, dataWizytyOd, dataWizytyDo, status, rodzaj);
    }

}
