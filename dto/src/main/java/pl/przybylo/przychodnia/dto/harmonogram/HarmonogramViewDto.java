package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class HarmonogramViewDto extends AbstractHarmonogramDto {

    private long id;

    public HarmonogramViewDto(long id, LocalDateTime obowiazujeOd, LocalDateTime obowiazujeDo, Set<HarmonogramPozycjaViewDto> pozycjaCollection) {
        super(obowiazujeOd, obowiazujeDo, pozycjaCollection);
        this.id = id;
    }

}
