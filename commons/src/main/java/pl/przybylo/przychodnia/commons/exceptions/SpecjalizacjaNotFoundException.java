package pl.przybylo.przychodnia.commons.exceptions;

public class SpecjalizacjaNotFoundException extends AppException {

    public SpecjalizacjaNotFoundException(long id) {
        super(String.format("Specjalizacja o identyfikatorze %d nie została odnaleziona.", id));
    }

}
