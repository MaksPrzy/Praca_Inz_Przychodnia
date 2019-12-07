package pl.przybylo.przychodnia.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static com.google.common.collect.Lists.newArrayList;
import static pl.przybylo.przychodnia.commons.normalize.NormalizePair.pairOf;
import static pl.przybylo.przychodnia.commons.normalize.NormalizeUtils.normalize;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Uzytkownik implements Searchable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String imie;
    private String nazwisko;

    @Column(columnDefinition = "text")
    private String fullTextSearch;

    @PrePersist
    public void prePersist() {
        fullTextSearch = normalizeUzytkownik();
    }

    @PreUpdate
    public void preUpdate() {
        fullTextSearch = normalizeUzytkownik();
    }

    private String normalizeUzytkownik() {
        return normalize(newArrayList(login), pairOf(imie, nazwisko));
    }

}
