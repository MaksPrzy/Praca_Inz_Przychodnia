package pl.przybylo.przychodnia.dto.gabinet;

import lombok.Data;

@Data
public class GabinetEditDto extends AbstractGabinetDto {

    private Long id;

    public GabinetEditDto(Long id, String nazwa, String opis, Integer pietro) {
        super(nazwa, opis, pietro);
        this.id = id;
    }

}
