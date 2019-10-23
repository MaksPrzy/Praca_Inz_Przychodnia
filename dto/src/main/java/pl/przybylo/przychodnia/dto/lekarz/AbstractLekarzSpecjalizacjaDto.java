package pl.przybylo.przychodnia.dto.lekarz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public abstract class AbstractLekarzSpecjalizacjaDto {

    protected Long id;
    protected int rokUzyskaniaDyplomuZeSpecjalizacji;

}
