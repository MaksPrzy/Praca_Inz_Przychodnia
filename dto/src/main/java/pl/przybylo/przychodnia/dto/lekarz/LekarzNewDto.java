package pl.przybylo.przychodnia.dto.lekarz;

import lombok.Data;

import java.util.Collection;

@Data
public class LekarzNewDto extends AbstractLekarzDto {

    public LekarzNewDto(String imie, String nazwisko, String numer, Collection<LekarzSpecjalizacjaNewDto> specjalizacjaCollection) {
        super(imie, nazwisko, numer, specjalizacjaCollection);
    }

}
