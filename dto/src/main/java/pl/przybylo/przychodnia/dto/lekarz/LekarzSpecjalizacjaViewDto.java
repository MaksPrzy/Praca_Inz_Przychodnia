package pl.przybylo.przychodnia.dto.lekarz;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LekarzSpecjalizacjaViewDto extends AbstractLekarzSpecjalizacjaDto {

    public LekarzSpecjalizacjaViewDto(Long id, String nazwa, int rokUzyskaniaDyplomuZeSpecjalizacji) {
        super(id, nazwa, rokUzyskaniaDyplomuZeSpecjalizacji);
    }

}
