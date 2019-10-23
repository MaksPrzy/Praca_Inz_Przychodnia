package pl.przybylo.przychodnia.business.impl.wizyta;

import
        org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.FieldError;
import pl.przybylo.przychodnia.dto.wizyta.ZakonczWizyteDto;

import java.util.Objects;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
public class ZakonczonaWizytaValidator {

    public void check(ZakonczWizyteDto zakonczWizyte) {
        checkNotNull(zakonczWizyte, "20191022235139");

        Set<FieldError> fieldErrorSet = newHashSet();

        if (Objects.isNull(zakonczWizyte.getWizytaId())) {
            fieldErrorSet.add(fieldRequired("wizyta"));
        }
        if (isBlank(zakonczWizyte.getKodIcd10())) {
            fieldErrorSet.add(fieldRequired("kod icd 10"));
        }
        if (isBlank(zakonczWizyte.getUwagi())) {
            fieldErrorSet.add(fieldRequired("uwagi"));
        }
    }
}
