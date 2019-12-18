package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;
import pl.przybylo.przychodnia.dto.wizyta.WizytaViewDto;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public abstract class AbstractHarmonogramPozycjaDto {


    protected GabinetViewDto gabinet;
    protected int dzienTygodnia;
    protected LocalTime godzinaOd;
    protected LocalTime godzinaDo;
    protected int interwalCzasowyWMinutach;

}
