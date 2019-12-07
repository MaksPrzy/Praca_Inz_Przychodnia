package pl.przybylo.przychodnia.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Adres {

    private String kodPocztowy;
    private String miejscowosc;
    private String ulica;
    private String numerDomu;
    private String numerLokalu;

}
