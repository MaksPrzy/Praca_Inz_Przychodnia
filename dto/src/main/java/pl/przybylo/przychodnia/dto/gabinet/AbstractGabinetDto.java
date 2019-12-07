package pl.przybylo.przychodnia.dto.gabinet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractGabinetDto {

    protected String nazwa;
    protected String opis;
    protected Integer pietro;

}
