package pl.przybylo.przychodnia.domain.model.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.przybylo.przychodnia.commons.exceptions.StatusNotFoundException;

import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

@Getter
@AllArgsConstructor
public enum Status {

    ZAPLANOWANA("zaplanowana", "Zaplanowana"),
    ZAKONCZONA("zakonczona", "Zakończona");

    private final String code;
    private final String name;

    public static Status findByCode(String code) {
        checkArgument(isNotBlank(code), "20191108173804");

        return Stream.of(values())
                .filter(status -> status.getCode().equals(code)).findFirst()
                .orElseThrow(() -> new StatusNotFoundException(code));
    }

    @Override
    public String toString() {
        return code;
    }

}
