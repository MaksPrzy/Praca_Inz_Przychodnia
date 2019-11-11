package pl.przybylo.przychodnia.dto.wizyta;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ZaplanujWizyteDto extends AbstractWizytaDto {

    public ZaplanujWizyteDto(Long pacjentId, Long lekarzId, Long specjalizacjaId,
                             Long gabinetId, LocalDateTime dataWizytyOd, LocalDateTime dataWizytyDo,
                             String status, String rodzaj) {
        super(pacjentId, lekarzId, specjalizacjaId, gabinetId, dataWizytyOd, dataWizytyDo, status, rodzaj);
    }

}
