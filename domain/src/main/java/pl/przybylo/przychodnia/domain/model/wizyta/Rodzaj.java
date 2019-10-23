package pl.przybylo.przychodnia.domain.model.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Rodzaj {

    PRYWATNA("prywatna", "Prywatna"),
    REFUNDOWANA("refundowana", "Refundowana");

    private final String code;
    private final String name;

    @Override
    public String toString() {
        return code;
    }

}
