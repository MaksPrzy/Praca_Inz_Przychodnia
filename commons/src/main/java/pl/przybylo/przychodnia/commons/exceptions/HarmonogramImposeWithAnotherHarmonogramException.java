package pl.przybylo.przychodnia.commons.exceptions;

public class HarmonogramImposeWithAnotherHarmonogramException extends AppException {

    public HarmonogramImposeWithAnotherHarmonogramException() {
        super("Harmonogram nakłada się z innym harmonogramem.");
    }

}
