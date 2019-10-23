package pl.przybylo.przychodnia.commons.exceptions;

public class HarmonogramIsAlreadyActiveException extends AppException {

    public HarmonogramIsAlreadyActiveException() {
        super("Harmonogram już obowiązuje.");
    }

}
