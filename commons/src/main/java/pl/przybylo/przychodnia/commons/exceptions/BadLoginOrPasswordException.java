package pl.przybylo.przychodnia.commons.exceptions;

public class BadLoginOrPasswordException extends AppException {

    public BadLoginOrPasswordException() {
        super(String.format("Podany login lub hasło są nieprawidłowe."));
    }

}
