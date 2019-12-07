package pl.przybylo.przychodnia.commons.exceptions;

public class StatusNotFoundException extends AppException {

    public StatusNotFoundException(String status) {
        super(String.format("Status wizyty o kodzie %s nie został odnaleziony.", status));
    }

}
