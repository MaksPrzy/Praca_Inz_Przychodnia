package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public abstract class AbstractHarmonogramPozycjaDto {

    protected long gabinetId;
    protected int dzienTygodnia;
    protected LocalTime godzinaOd;
    protected LocalTime godzinaDo;
    protected int interwalCzasowyWMinutach;

}
