package pl.przybylo.przychodnia.commons.exceptions;

public class CantDeleteWizytaException extends AppException {

    public CantDeleteWizytaException(long wizytaId) {
        super(String.format("Nie można usunąć zaplanowanej wizyty o identyfikatorze %d.", wizytaId));
    }

}
