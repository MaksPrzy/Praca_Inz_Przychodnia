package pl.przybylo.przychodnia.commons.exceptions;

public class WizytaNotFoundException extends AppException {

    public WizytaNotFoundException (long id) {
        super(String.format("Wizyta o identyfikatorze %d nie została odnaleziona.", id));
    }
}
