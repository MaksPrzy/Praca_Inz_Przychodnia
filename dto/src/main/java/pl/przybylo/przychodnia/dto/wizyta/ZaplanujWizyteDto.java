package pl.przybylo.przychodnia.dto.wizyta;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ZaplanujWizyteDto extends AbstractWizytaDto {

    public ZaplanujWizyteDto(Long pacjentId, Long lekarzId, Long specjalizacjaId,
                             Long gabinet, LocalDateTime dataWizytyOd, LocalDateTime dataWizytyDo, String rodzaj) {
        super(pacjentId, lekarzId, specjalizacjaId, gabinet, dataWizytyOd, dataWizytyDo, rodzaj);
    }

}
