package pl.przybylo.przychodnia.commons.exceptions;

public class RodzajNotFoundException extends AppException {

    public RodzajNotFoundException(String code) {
        super(String.format("Rodzaj o podanym %s nie został odnaleziony.", code));
    }
}
