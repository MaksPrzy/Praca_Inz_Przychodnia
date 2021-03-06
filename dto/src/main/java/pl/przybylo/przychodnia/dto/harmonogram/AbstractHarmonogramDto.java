package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public abstract class AbstractHarmonogramDto {

    protected LocalDateTime obowiazujeOd;
    protected LocalDateTime obowiazujeDo;
    protected List<? extends AbstractHarmonogramPozycjaDto> pozycjaCollection;

}
