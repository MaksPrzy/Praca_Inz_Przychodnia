package pl.przybylo.przychodnia.domain.model.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.przybylo.przychodnia.commons.exceptions.RodzajNotFoundException;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Rodzaj {

    PRYWATNA("prywatna", "Prywatna"),
    REFUNDOWANA("refundowana", "Refundowana");

    public final String code;
    public final String name;

    public static Rodzaj findByCode(String code) {
        return Stream.of(values()).filter(rodzaj -> rodzaj.getCode().equals(code)).findFirst().
                orElseThrow(() -> new RodzajNotFoundException(code));
    }
}

