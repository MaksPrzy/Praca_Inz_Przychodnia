package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class HarmonogramNewDto extends AbstractHarmonogramDto {

    private long lekarzId;
    private boolean shouldCheckDatesImpose; // uzytkownik akceptuje, ze daty w harmonogramach nakladaja sie na siebie

    public HarmonogramNewDto(long lekarzId, LocalDateTime obowiazujeOd, LocalDateTime obowiazujeDo,
                             Set<HarmonogramPozycjaNewDto> pozycjaCollection, boolean shouldCheckDatesImpose) {
        super(obowiazujeOd, obowiazujeDo, pozycjaCollection);
        this.lekarzId = lekarzId;
        this.shouldCheckDatesImpose = shouldCheckDatesImpose;
    }

}
