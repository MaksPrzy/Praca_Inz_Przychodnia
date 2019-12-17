package pl.przybylo.przychodnia.dto.harmonogram;

import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;

import java.time.LocalTime;

public class HarmonogramPozycjaNewDto extends AbstractHarmonogramPozycjaDto {

    public HarmonogramPozycjaNewDto(GabinetViewDto gabinet, int dzienTygodnia, LocalTime godzinaOd, LocalTime godzinaDo, int interwalCzasowyWMinutach) {
        super(gabinet, dzienTygodnia, godzinaOd, godzinaDo, interwalCzasowyWMinutach);
    }

}
