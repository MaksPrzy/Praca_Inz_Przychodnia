package pl.przybylo.przychodnia.dto.harmonogram;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class HarmonogramEditDto extends AbstractHarmonogramDto {

    private long id;
    private long lekarzId;
    private boolean shouldCheckDatesImpose; // uzytkownik akceptuje, ze daty w harmonogramach nakladaja sie na siebie

    public HarmonogramEditDto(long id, long lekarzId, LocalDateTime obowiazujeOd, LocalDateTime obowiazujeDo,
                              Set<HarmonogramPozycjaEditDto> pozycjaCollection, boolean shouldCheckDatesImpose) {
        super(obowiazujeOd, obowiazujeDo, pozycjaCollection);
        this.id = id;
        this.lekarzId = lekarzId;
        this.shouldCheckDatesImpose = shouldCheckDatesImpose;
    }

}
