package pl.przybylo.przychodnia.dto.lekarz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public abstract class AbstractLekarzDto {

    protected String imie;
    protected String nazwisko;
    protected String numer;
    protected Collection<? extends AbstractLekarzSpecjalizacjaDto> specjalizacjaCollection;

}
