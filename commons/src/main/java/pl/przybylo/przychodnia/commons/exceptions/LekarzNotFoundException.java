package pl.przybylo.przychodnia.commons.exceptions;

public class LekarzNotFoundException extends AppException {

    public LekarzNotFoundException(long id) {
        super(String.format("Lekarz o identyfikatorze %d nie został odnaleziony.", id));
    }

}
