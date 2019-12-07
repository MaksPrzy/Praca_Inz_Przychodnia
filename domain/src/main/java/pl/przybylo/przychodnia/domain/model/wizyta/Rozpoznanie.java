package pl.przybylo.przychodnia.domain.model.wizyta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Rozpoznanie {

    private String kodIcd10;

    @Column(columnDefinition = "text")
    private String uwagi;

}
