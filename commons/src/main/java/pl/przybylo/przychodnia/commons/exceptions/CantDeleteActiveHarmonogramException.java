package pl.przybylo.przychodnia.commons.exceptions;

public class CantDeleteActiveHarmonogramException extends AppException {

    public CantDeleteActiveHarmonogramException() {
        super("Nie można usunąć harmonogramu który już obowiązuje.");
    }

}
