package pl.przybylo.przychodnia.commons.exceptions;

import lombok.Getter;

import java.util.Set;

@Getter
public class AppFieldException extends RuntimeException {

    private Set<FieldError> fieldErrorCollection;

    public AppFieldException(Set<FieldError> fieldErrorCollection) {
        this.fieldErrorCollection = fieldErrorCollection;
    }

}
