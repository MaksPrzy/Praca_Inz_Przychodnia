package pl.przybylo.przychodnia.dto.lekarz;

import lombok.Data;

import java.util.Set;

@Data
public class LekarzDetailViewDto extends AbstractLekarzDto {

    private Long id;

    public LekarzDetailViewDto(Long id, String imie, String nazwisko, String numer, Set<? extends AbstractLekarzSpecjalizacjaDto> specjalizacjaCollection) {
        super(imie, nazwisko, numer, specjalizacjaCollection);
        this.id = id;
    }

}
