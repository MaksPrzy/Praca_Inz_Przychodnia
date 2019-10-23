package pl.przybylo.przychodnia.commons.exceptions;

public class HarmonogramNotFoundException extends AppException {

    public HarmonogramNotFoundException(long id) {
        super(String.format("Harmonogram o identyfikatorze %d nie został odnaleziony.", id));
    }

}
