package pl.przybylo.przychodnia.business.impl.gabinet;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.AppFieldException;
import pl.przybylo.przychodnia.commons.exceptions.FieldError;
import pl.przybylo.przychodnia.dto.gabinet.AbstractGabinetDto;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
public class GabinetValidator {

    public void check(AbstractGabinetDto gabinetDto) {
        checkNotNull(gabinetDto, "20190712172005");

        Set<FieldError> fieldErrorSet = newHashSet();

        if (isBlank(gabinetDto.getNazwa())) {
            fieldErrorSet.add(fieldRequired("nazwa"));
        }

        if (isBlank(gabinetDto.getOpis())) {
            fieldErrorSet.add(fieldRequired("opis"));
        }

        if (isNull(gabinetDto.getPietro())) {
            fieldErrorSet.add(fieldRequired("pietro"));
        }

        if (CollectionUtils.isEmpty(fieldErrorSet)) {
            throw new AppFieldException(fieldErrorSet);
        }
    }

}
