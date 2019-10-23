package pl.przybylo.przychodnia.dto.lekarz;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
public class LekarzEditDto extends AbstractLekarzDto {

    private Long id;

    public LekarzEditDto(Long id, String imie, String nazwisko, String numer, Collection<LekarzSpecjalizacjaNewDto> specjalizacjaCollection) {
        super(imie, nazwisko, numer, specjalizacjaCollection);
        this.id = id;
    }

}
