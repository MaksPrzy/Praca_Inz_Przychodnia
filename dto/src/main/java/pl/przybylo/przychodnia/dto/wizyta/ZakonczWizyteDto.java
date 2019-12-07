package pl.przybylo.przychodnia.dto.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ZakonczWizyteDto {

    private Long wizytaId;
    private String kodIcd10;  //rozpoznanie choroby po kodzie od lekarza
    private String uwagi;

}
