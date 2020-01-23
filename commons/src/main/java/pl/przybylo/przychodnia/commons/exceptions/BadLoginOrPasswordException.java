package pl.przybylo.przychodnia.commons.exceptions;

public class BadLoginOrPasswordException extends AppException {

    public BadLoginOrPasswordException() {
        super(String.format("Podany e-mail lub hasło są nieprawidłowe."));
    }

}
