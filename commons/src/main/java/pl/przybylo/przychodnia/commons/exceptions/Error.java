package pl.przybylo.przychodnia.commons.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder(toBuilder = true)
public class Error {

    private String message;
    private Set<FieldError> fieldErrorCollection;

}
