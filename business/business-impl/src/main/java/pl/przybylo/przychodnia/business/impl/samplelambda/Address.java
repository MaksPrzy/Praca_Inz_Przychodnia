package pl.przybylo.przychodnia.business.impl.samplelambda;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Address {

    private final String city;
    private final String street;

}
