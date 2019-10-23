package pl.przybylo.przychodnia.business.impl.lekarz;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.FieldError;
import pl.przybylo.przychodnia.dto.lekarz.AbstractLekarzDto;
import pl.przybylo.przychodnia.dto.lekarz.AbstractLekarzSpecjalizacjaDto;
import pl.przybylo.przychodnia.commons.exceptions.AppException;
import pl.przybylo.przychodnia.commons.exceptions.AppFieldException;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
public class LekarzValidator {

    private static final int MIN_DOSWIADCZENIE_IN_YEARS = 0;

    public void check(AbstractLekarzDto lekarzDto) {
        checkNotNull(lekarzDto, "20190712165737");

        Set<FieldError> fieldErrorSet = newHashSet();

        if (isBlank(lekarzDto.getImie())) {
            fieldErrorSet.add(fieldRequired("imie"));
        }

        if (isBlank(lekarzDto.getNazwisko())) {
            fieldErrorSet.add(fieldRequired("nazwisko"));
        }

        if (isBlank(lekarzDto.getNumer())) {
            fieldErrorSet.add(fieldRequired("numer"));
        }

        lekarzDto.getSpecjalizacjaCollection().forEach(s -> checkSpecjalizacja(s, fieldErrorSet));

        if (!CollectionUtils.isEmpty(fieldErrorSet)) {
            throw new AppFieldException(fieldErrorSet);
        }
    }

    private void checkSpecjalizacja(AbstractLekarzSpecjalizacjaDto lekarzSpecjalizacjaNewDto, Set<FieldError> fieldErrorSet) {
        if (lekarzSpecjalizacjaNewDto.getRokUzyskaniaDyplomuZeSpecjalizacji() < MIN_DOSWIADCZENIE_IN_YEARS) {
            fieldErrorSet.add(new FieldError("rokUzyskaniaDyplomuZeSpecjalizacji", String.format("Minimalne doświadczenie powinno być większe lub równe %d (w latach).",
                    MIN_DOSWIADCZENIE_IN_YEARS)));
        }
    }

}
