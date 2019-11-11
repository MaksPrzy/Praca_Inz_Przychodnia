package pl.przybylo.przychodnia.dto.wizyta;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class WizytaEditDto extends AbstractWizytaDto {

    public WizytaEditDto(Long pacjentId, Long lekarzId, Long specjalizacjaId, Long gabinetId,
                         LocalDateTime dataWizytyOd, LocalDateTime dataWizytyDo,
                         String status, String rodzaj) {
        super(pacjentId, lekarzId, specjalizacjaId, gabinetId, dataWizytyOd, dataWizytyDo, status, rodzaj);
    }

    private Long id;

}
