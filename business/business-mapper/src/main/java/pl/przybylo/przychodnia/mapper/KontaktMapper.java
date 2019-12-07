package pl.przybylo.przychodnia.mapper;

import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.domain.model.Kontakt;
import pl.przybylo.przychodnia.dto.pacjent.KontaktDto;

import static java.util.Objects.isNull;

@Component
public class KontaktMapper {

    public Kontakt map(KontaktDto kontaktDto) {
        if (isNull(kontaktDto)) {
            return null;
        }

        return new Kontakt(kontaktDto.getEmail(),
                kontaktDto.getTelefonKomorkowy(),
                kontaktDto.getTelefonStacjonarny()
        );
    }

    public KontaktDto map(Kontakt kontakt) {
        if (isNull(kontakt)) {
            return null;
        }

        return new KontaktDto(kontakt.getEmail(),
                kontakt.getTelefonKomorkowy(),
                kontakt.getTelefonStacjonarny());
    }

}
