package pl.przybylo.przychodnia.commons.exceptions;

public class PacjentNotFoundException extends AppException {

    public PacjentNotFoundException(long id) {
        super(String.format("Pacjent o identyfikatorze %d nie został odnaleziony.", id));
    }

}
