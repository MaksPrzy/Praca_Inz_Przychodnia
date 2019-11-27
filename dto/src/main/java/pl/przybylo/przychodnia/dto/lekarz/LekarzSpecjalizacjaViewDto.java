package pl.przybylo.przychodnia.dto.lekarz;

import lombok.Data;

@Data
public class LekarzSpecjalizacjaViewDto extends AbstractLekarzSpecjalizacjaDto {

    public LekarzSpecjalizacjaViewDto(Long id, String nazwa, int rokUzyskaniaDyplomuZeSpecjalizacji) {
        super(id, nazwa, rokUzyskaniaDyplomuZeSpecjalizacji);
    }

}
