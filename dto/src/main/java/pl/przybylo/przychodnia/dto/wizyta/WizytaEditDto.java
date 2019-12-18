package pl.przybylo.przychodnia.dto.wizyta;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WizytaEditDto extends AbstractWizytaDto {

    public WizytaEditDto(Long id, Long pacjentId, Long lekarzId, Long specjalizacjaId, Long gabinet,
                         LocalDateTime dataWizytyOd, LocalDateTime dataWizytyDo, String rodzaj, String status) {
        super(pacjentId, lekarzId, specjalizacjaId, gabinet, dataWizytyOd, dataWizytyDo, rodzaj);
        this.id = id;
        this.status = status;
    }

    private Long id;
    private String status;

}
