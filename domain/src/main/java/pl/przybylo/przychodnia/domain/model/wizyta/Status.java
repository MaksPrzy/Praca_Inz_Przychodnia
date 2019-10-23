package pl.przybylo.przychodnia.domain.model.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

    ZAPLANOWANA("zaplanowana", "Zaplanowana"),
    ZAKONCZONA("zakonczona", "Zakończona");

    private final String code;
    private final String name;

    @Override
    public String toString() {
        return code;
    }

}
