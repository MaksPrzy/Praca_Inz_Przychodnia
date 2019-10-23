package pl.przybylo.przychodnia.dto.lekarz;

import lombok.Data;

@Data
public class LekarzSpecjalizacjaViewDto extends AbstractLekarzSpecjalizacjaDto {

    private String nazwa;

    public LekarzSpecjalizacjaViewDto(Long id, String nazwa, int rokUzyskaniaDyplomuZeSpecjalizacji) {
        super(id, rokUzyskaniaDyplomuZeSpecjalizacji);
        this.nazwa = nazwa;
    }

}
