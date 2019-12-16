package pl.przybylo.przychodnia.dto.wizyta;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class WizytaEditDto extends AbstractWizytaDto {

    public WizytaEditDto(Long id, Long pacjentId, Long lekarzId, Long specjalizacjaId, Long gabinetId,
                         LocalDateTime dataWizytyOd, LocalDateTime dataWizytyDo, String rodzaj, String status) {
        super(pacjentId, lekarzId, specjalizacjaId, gabinetId, dataWizytyOd, dataWizytyDo, rodzaj);
        this.id = id;
        this.status = status;
    }

    private Long id;
    private String status;

}
