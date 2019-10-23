package pl.przybylo.przychodnia.business.impl.wizyta;

import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.FieldError;
import pl.przybylo.przychodnia.dto.wizyta.ZaplanujWizyteDto;

import java.util.Objects;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;


@Component
public class ZaplanowanaWizytaValidator {

    public void check(ZaplanujWizyteDto zaplanujWizyte) {
        checkNotNull(zaplanujWizyte, "20191023002804");

        Set<FieldError> fieldErrorSet = newHashSet();


        if (Objects.isNull(zaplanujWizyte.getPacjentId())) {
            fieldErrorSet.add(fieldRequired("pacjent id"));
        }
        if (Objects.isNull(zaplanujWizyte.getLekarzId())) {
            fieldErrorSet.add(fieldRequired("lekarz id"));
        }
        if (Objects.isNull(zaplanujWizyte.getSpecjalizacjaId())) {
            fieldErrorSet.add(fieldRequired("specjalizacja id"));
        }
        if (Objects.isNull(zaplanujWizyte.getGabinetId())) {
            fieldErrorSet.add(fieldRequired("gabinet id"));
//        }
//        if ((zaplanujWizyte.getDataWizytyOd())) {
//            fieldErrorSet.add(fieldRequired("data wizyty od"));
//        }
//        if ((zaplanujWizyte.getDataWizytyDo())) {
//            fieldErrorSet.add(fieldRequired("data wizyty do"));
        }
        if (isBlank(zaplanujWizyte.getRodzaj())) {
            fieldErrorSet.add(fieldRequired("rodzaj wizyty"));
        }
    }
}
