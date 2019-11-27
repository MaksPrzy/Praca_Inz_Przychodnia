package pl.przybylo.przychodnia.dto.lekarz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class AbstractLekarzSpecjalizacjaDto {

    protected Long id;
    protected String nazwa;
    protected int rokUzyskaniaDyplomuZeSpecjalizacji;

}
