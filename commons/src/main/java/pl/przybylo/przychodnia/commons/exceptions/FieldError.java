package pl.przybylo.przychodnia.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

@Getter
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
public class FieldError {

    private static final String REQUIRED = "Pole jest wymagane.";

    private final String name;          // np. firstName
    private final String message;       // np. Pole jest wymagane.

    public static FieldError of(String name, String message) {
        return new FieldError(name, message);
    }

    public static FieldError fieldRequired(String name) {
        checkArgument(isNotBlank(name), "20190718194345");

        return new FieldError(name, REQUIRED);
    }

}
