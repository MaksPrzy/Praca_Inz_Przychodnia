package pl.przybylo.przychodnia.business.impl.wizyta;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.AppFieldException;
import pl.przybylo.przychodnia.commons.exceptions.FieldError;
import pl.przybylo.przychodnia.dto.wizyta.AbstractWizytaDto;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
public class WizytaValidator {

    public void check(AbstractWizytaDto wizytaDto) {
        checkNotNull(wizytaDto, "20191005151849");

        Set<FieldError> fieldErrorSet = newHashSet();

        if (isNull(wizytaDto.getPacjent())) {
            fieldErrorSet.add(fieldRequired("pacjent"));
        }

        if (isNull(wizytaDto.getLekarz())) {
            fieldErrorSet.add(fieldRequired("lekarz"));
        }

        if (isNull(wizytaDto.getSpecjalizacja())) {
            fieldErrorSet.add(fieldRequired("specjalizacja"));
        }

        if (isNull(wizytaDto.getGabinet())) {
            fieldErrorSet.add(fieldRequired("gabinet"));
        }

        if (isNull(wizytaDto.getDataWizytyOd())) {
            fieldErrorSet.add(fieldRequired("data rozpoczęcia wizyty"));
        }

        if (isNull(wizytaDto.getDataWizytyDo())) {
            fieldErrorSet.add(fieldRequired("data zakończenia wizyty"));
        }

        if (isNull(wizytaDto.getFaktycznaDataWizytyDo)) {
            fieldErrorSet.add(fieldRequired("data zakończenia wizyty"));
        }

        if ()

        if (CollectionUtils.isEmpty(fieldErrorSet)) {
            throw new AppFieldException(fieldErrorSet);
        }
    }

}
