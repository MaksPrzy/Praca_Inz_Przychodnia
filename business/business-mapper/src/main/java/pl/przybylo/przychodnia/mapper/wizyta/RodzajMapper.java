package pl.przybylo.przychodnia.mapper.wizyta;

import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.domain.model.wizyta.Rodzaj;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class RodzajMapper {

    public String map(Rodzaj rodzaj) {
        if (isNull(rodzaj)) {
            return null;
        }
        return rodzaj.getCode();
    }

    public Rodzaj map(String rodzaj) {
        if (isNotBlank(rodzaj)) {
            return null;
        }
        return Rodzaj.findByCode(rodzaj);
    }
}


