package pl.przybylo.przychodnia.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.przybylo.przychodnia.commons.normalize.NormalizeUtils.normalize;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Specjalizacja implements Searchable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    @Column(columnDefinition = "text")
    private String fullTextSearch;

    public Specjalizacja(String nazwa) {
        checkArgument(isNotBlank(nazwa), "20190605204625");
        this.nazwa = nazwa;
    }

    @PrePersist
    public void prePersist() {
        fullTextSearch = normalizeSpecjalizacja();
    }

    @PreUpdate
    public void preUpdate() {
        fullTextSearch = normalizeSpecjalizacja();
    }

    public String normalizeSpecjalizacja() {
        return normalize(nazwa);
    }

}
