package pl.przybylo.przychodnia.business.impl.pacjent;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.business.impl.DateNotFromFutureValidator;
import pl.przybylo.przychodnia.commons.exceptions.FieldError;
import pl.przybylo.przychodnia.dto.pacjent.AbstractPacjentDto;
import pl.przybylo.przychodnia.commons.exceptions.AppException;
import pl.przybylo.przychodnia.commons.exceptions.AppFieldException;
import pl.przybylo.przychodnia.dto.pacjent.AdresDto;

import java.time.LocalDate;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class PacjentValidator {

    private static final String PESEL_FIELD = "pesel";
    private static final String DATA_URODZENIA_FIELD = "dataUrodzenia";

    private final PeselValidator peselValidator;
    private final DateNotFromFutureValidator dateNotFromFutureValidator;

    public void check(AbstractPacjentDto pacjentDto) {
        checkNotNull(pacjentDto, "20190606201449");

        Set<FieldError> fieldErrorSet = newHashSet();

        checkPesel(pacjentDto.getPesel(), fieldErrorSet);

        if (isBlank(pacjentDto.getImie())) {
            fieldErrorSet.add(fieldRequired("imie"));
        }

        if (isBlank(pacjentDto.getNazwisko())) {
            fieldErrorSet.add(fieldRequired("nazwisko"));
        }

        checkDataUrodzenia(pacjentDto.getDataUrodzenia(), fieldErrorSet);

        checkAdres(pacjentDto.getAdres(), fieldErrorSet);

        if (!CollectionUtils.isEmpty(fieldErrorSet)) {
            throw new AppFieldException(fieldErrorSet);
        }
    }

    private void checkPesel(String pesel, Set<FieldError> fieldErrorSet) {
        if (isBlank(pesel)) {
            fieldErrorSet.add(fieldRequired(PESEL_FIELD));
        } else {
            if (!peselValidator.valid(pesel)) {
                fieldErrorSet.add(new FieldError(PESEL_FIELD, "PESEL musi być prawidłowy!"));
            }
        }
    }

    private void checkAdres(AdresDto adres, Set<FieldError> fieldErrorSet) {
        if (isNull(adres)) {
            fieldErrorSet.add(new FieldError("adres", "Adres jest wymagany."));
        } else {
            if (isBlank(adres.getKodPocztowy())) {
                fieldErrorSet.add(fieldRequired("adres_kodPocztowy"));
            }

            if (isBlank(adres.getMiejscowosc())) {
                fieldErrorSet.add(fieldRequired("adres_miejscowosc"));
            }

            if (isBlank(adres.getNumerDomu())) {
                fieldErrorSet.add(fieldRequired("adres_numerDomu"));
            }
        }
    }

    private void checkDataUrodzenia(LocalDate dataUrodzenia, Set<FieldError> fieldErrorSet) {
        if (isNull(dataUrodzenia)) {
            fieldErrorSet.add(fieldRequired(DATA_URODZENIA_FIELD));
        } else {
            if (!dateNotFromFutureValidator.valid(dataUrodzenia)) {
                fieldErrorSet.add(new FieldError(DATA_URODZENIA_FIELD, "Data nie może być z przyszłości."));
            }
        }
    }

}
