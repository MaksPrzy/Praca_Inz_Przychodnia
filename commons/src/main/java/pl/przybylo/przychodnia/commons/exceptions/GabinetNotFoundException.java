package pl.przybylo.przychodnia.commons.exceptions;

public class GabinetNotFoundException extends AppException {

    public GabinetNotFoundException(long id) {
        super(String.format("Gabinet o identyfikatorze %d nie został odnaleziony.", id));
    }

}
