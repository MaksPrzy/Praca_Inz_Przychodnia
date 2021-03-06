package pl.przybylo.przychodnia.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import pl.przybylo.przychodnia.commons.exceptions.AppException;
import pl.przybylo.przychodnia.commons.exceptions.AppFieldException;
import pl.przybylo.przychodnia.commons.exceptions.Error;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler({AppFieldException.class})
    public ResponseEntity<Error> handle(AppFieldException appFieldException) {
        checkNotNull(appFieldException, "20191015193252");

        return new ResponseEntity<>(Error.builder()
                .fieldErrorCollection(appFieldException.getFieldErrorCollection())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AppException.class})
    public ResponseEntity<Error> handle(AppException appException) {
        checkNotNull(appException, "20191015193647");

        return new ResponseEntity<>(Error.builder()
                .message(appException.getMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

}
