package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HarmonogramViewDto extends AbstractHarmonogramDto {

    private long id;
    private long specjalizacjaId;

    public HarmonogramViewDto(long id, long specjalizacjaId, LocalDateTime obowiazujeOd, LocalDateTime obowiazujeDo, List<HarmonogramPozycjaViewDto> pozycjaCollection) {
        super(obowiazujeOd, obowiazujeDo, pozycjaCollection);
        this.id = id;
        this.specjalizacjaId = specjalizacjaId;
    }

}
