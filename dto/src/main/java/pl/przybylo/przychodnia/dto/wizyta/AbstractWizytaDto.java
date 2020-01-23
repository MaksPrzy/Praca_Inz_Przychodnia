package pl.przybylo.przychodnia.dto.wizyta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractWizytaDto {

    protected Long pacjentId;
    protected Long lekarzId;
    protected Long specjalizacjaId;
    protected Long gabinet;
    protected LocalDateTime dataWizytyOd;
    protected LocalDateTime dataWizytyDo;
    //    protected String status;
    protected String rodzaj;

}
