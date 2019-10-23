package pl.przybylo.przychodnia.dto.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ZaplanujWizyteDto {

    private final Long pacjentId;
    private final Long lekarzId;
    private final Long specjalizacjaId;
    private final Long gabinetId;
    private final LocalDateTime dataWizytyOd;
    private final LocalDateTime dataWizytyDo;
    private final String rodzaj;

}
