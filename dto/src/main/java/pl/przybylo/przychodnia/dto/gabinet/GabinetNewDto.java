package pl.przybylo.przychodnia.dto.gabinet;

import lombok.Data;

@Data
public class GabinetNewDto extends AbstractGabinetDto {

    public GabinetNewDto(String nazwa, String opis, Integer pietro) {
        super(nazwa, opis, pietro);
    }

}
