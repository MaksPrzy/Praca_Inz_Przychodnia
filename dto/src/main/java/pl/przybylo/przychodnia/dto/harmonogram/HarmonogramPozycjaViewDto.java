package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.Data;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;

import java.time.LocalTime;

@Data
public class HarmonogramPozycjaViewDto extends AbstractHarmonogramPozycjaDto {


    public HarmonogramPozycjaViewDto(GabinetViewDto gabinet, int dzienTygodnia, LocalTime godzinaOd, LocalTime godzinaDo, int interwalCzasowyWMinutach) {
        super(gabinet, dzienTygodnia, godzinaOd, godzinaDo, interwalCzasowyWMinutach);

    }

}
