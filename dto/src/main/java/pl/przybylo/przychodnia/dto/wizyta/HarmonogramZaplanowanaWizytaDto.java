package pl.przybylo.przychodnia.dto.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HarmonogramZaplanowanaWizytaDto {

    private int dayIndex;
    private int minuteFrom;
    private int minuteTo;

}
