package pl.przybylo.przychodnia.business.impl.wizyta;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.AppFieldException;
import pl.przybylo.przychodnia.commons.exceptions.FieldError;
import pl.przybylo.przychodnia.dto.wizyta.AbstractWizytaDto;
import pl.przybylo.przychodnia.dto.wizyta.WizytaEditDto;
import pl.przybylo.przychodnia.dto.wizyta.ZakonczWizyteDto;
import pl.przybylo.przychodnia.dto.wizyta.ZaplanujWizyteDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.of;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
public class WizytaValidator {

    private static final String DATA_ZAKONCZENIA_WIZYTY_DO_FIELD = "data zakończenia wizyty";

    public void checkZaplanuj(ZaplanujWizyteDto zaplanujWizyteDto) {
        checkNotNull(zaplanujWizyteDto, "20191026145143");

        Set<FieldError> fieldErrorSet = newHashSet();

        check(fieldErrorSet, zaplanujWizyteDto);

        throwExceptionIfErrorsOccurs(fieldErrorSet);
    }

    public void checkUpdate(WizytaEditDto wizytaEditDto) {
        checkNotNull(wizytaEditDto, "20191005151849");

        Set<FieldError> fieldErrorSet = newHashSet();
        check(fieldErrorSet, wizytaEditDto);

        throwExceptionIfErrorsOccurs(fieldErrorSet);
    }

    public void checkZakoncz(ZakonczWizyteDto zakonczWizyte) {
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

        throwExceptionIfErrorsOccurs(fieldErrorSet);
    }

    private void check(Set<FieldError> fieldErrorSet, AbstractWizytaDto wizytaDto) {
        if (isNull(wizytaDto.getPacjentId())) {
            fieldErrorSet.add(fieldRequired("pacjent"));
        }

        if (isNull(wizytaDto.getLekarzId())) {
            fieldErrorSet.add(fieldRequired("lekarz"));
        }

        if (isNull(wizytaDto.getSpecjalizacjaId())) {
            fieldErrorSet.add(fieldRequired("specjalizacja"));
        }

        if (isNull(wizytaDto.getGabinet())) {
            fieldErrorSet.add(fieldRequired("gabinet"));
        }

        LocalDateTime dataWizytyOd = wizytaDto.getDataWizytyOd();
        LocalDateTime dataWizytyDo = wizytaDto.getDataWizytyDo();

        if (isNull(dataWizytyOd)) {
            fieldErrorSet.add(fieldRequired("data rozpoczęcia wizyty"));
        }

        if (isNull(dataWizytyDo)) {
            fieldErrorSet.add(fieldRequired(DATA_ZAKONCZENIA_WIZYTY_DO_FIELD));
        }

        if (nonNull(dataWizytyOd) && nonNull(dataWizytyDo) && dataWizytyDo.isBefore(dataWizytyOd)) {
            fieldErrorSet.add(of(DATA_ZAKONCZENIA_WIZYTY_DO_FIELD, "Data zakończenia wizyty nie może być wcześniejsza niż data rozpoczęcia wizyty."));
        }

        if (isBlank(wizytaDto.getRodzaj())) {
            fieldErrorSet.add(fieldRequired("rodzaj wizyty"));
        }
    }

    private void throwExceptionIfErrorsOccurs(Set<FieldError> fieldErrorSet) {
        if (CollectionUtils.isEmpty(fieldErrorSet)) {
            throw new AppFieldException(fieldErrorSet);
        }
    }

}
