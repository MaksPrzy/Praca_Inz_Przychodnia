package pl.przybylo.przychodnia.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.przybylo.przychodnia.commons.normalize.NormalizePair.pairOf;
import static pl.przybylo.przychodnia.commons.normalize.NormalizeUtils.normalize;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Lekarz implements Searchable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;

    private String nazwisko;

    @Column(name = "numer_wykonywania_zawodu")
    private String numer;

    @OneToMany
    @JoinTable(name = "lekarz_specjalizacja",
            joinColumns = {@JoinColumn(name = "lekarz_id")},
            inverseJoinColumns = {@JoinColumn(name = "specjalizacja_id")})
    private Set<LekarzSpecjalizacja> specjalizacjaCollection = new HashSet<>();

    @OneToMany
    @JoinTable(name = "lekarz_harmonogram",
            joinColumns = {@JoinColumn(name = "lekarz_id")},
            inverseJoinColumns = {@JoinColumn(name = "harmonogram_id")})
    private Set<Harmonogram> harmonogramCollection = new HashSet<>();

    @Column(columnDefinition = "text")
    private String fullTextSearch;

    public Lekarz(String imie, String nazwisko, String numer, Set<LekarzSpecjalizacja> specjalizacjaCollection) {
        checkArgument(isNotBlank(imie), "20190531194737");
        checkArgument(isNotBlank(nazwisko), "20190531194753");
        checkArgument(isNotBlank(numer), "20190531194806");
        checkArgument(!CollectionUtils.isEmpty(specjalizacjaCollection), "20190712210405");

        this.imie = checkNotNull(imie,"20190609210452");
        this.nazwisko = checkNotNull(nazwisko,"20190609210505");
        this.numer = checkNotNull(numer, "20190609210747");
        this.specjalizacjaCollection = specjalizacjaCollection;
    }

    @PrePersist
    public void prePersist() {
        fullTextSearch = normalizeLekarz();
    }

    @PreUpdate
    public void preUpdate() {
        fullTextSearch = normalizeLekarz();
    }

    private String normalizeLekarz() {
        Set<String> specjalizacjaNormalizedSet = specjalizacjaCollection.stream()
                .map(s -> s.getSpecjalizacjaNormalized())
                .collect(toSet());
        return normalize(specjalizacjaNormalizedSet, pairOf(imie, nazwisko));
    }

}
