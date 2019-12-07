package pl.przybylo.przychodnia.dto.gabinet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GabinetViewDto extends AbstractGabinetDto {

    private Long id;

    public GabinetViewDto(Long id, String nazwa, String opis, Integer pietro) {
        super(nazwa, opis, pietro);
        this.id = id;
    }

}
