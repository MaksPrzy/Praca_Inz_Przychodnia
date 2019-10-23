package pl.przybylo.przychodnia.dto.harmonogram;

import java.time.LocalTime;

public class HarmonogramPozycjaEditDto extends AbstractHarmonogramPozycjaDto {

    public HarmonogramPozycjaEditDto(long gabinetId, int dzienTygodnia, LocalTime godzinaOd, LocalTime godzinaDo, int interwalCzasowyWMinutach) {
        super(gabinetId, dzienTygodnia, godzinaOd, godzinaDo, interwalCzasowyWMinutach);
    }
    
}
