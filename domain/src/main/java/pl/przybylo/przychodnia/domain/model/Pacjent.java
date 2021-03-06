package pl.przybylo.przychodnia.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.przybylo.przychodnia.commons.normalize.NormalizePair.pairOf;
import static pl.przybylo.przychodnia.commons.normalize.NormalizeUtils.normalize;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pacjent implements Searchable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numerKartoteki;
    private String pesel;
    private String imie;
    private String nazwisko;
    private LocalDate dataUrodzenia;
    private String email;
    private String haslo;
    private String telefonKomorkowy;

    @Embedded
    private Adres adres;

    @Column(columnDefinition = "text")
    private String fullTextSearch;

    public Pacjent(String numerKartoteki, String pesel, String imie, String nazwisko, LocalDate dataUrodzenia,
                   String email, String haslo, String telefonKomorkowy, Adres adres) {
        checkArgument(isNotBlank(numerKartoteki), "20190606193118");
        checkArgument(isNotBlank(pesel), "20190606193956");
        checkArgument(isNotBlank(imie), "20190605204625");
        checkArgument(isNotBlank(nazwisko), "20190605204625");
        checkArgument(isNotBlank(email), "20191209202514");
        checkArgument(isNotBlank(haslo), "20191209202537");
        checkArgument(isNotBlank(telefonKomorkowy), "20191215132114");

        this.numerKartoteki = numerKartoteki;
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = checkNotNull(email, "20191210204056");
        this.haslo = checkNotNull(haslo, "20191210204110");
        this.telefonKomorkowy = checkNotNull(telefonKomorkowy, "20191215132141");
        this.dataUrodzenia = checkNotNull(dataUrodzenia, "20190606182105");
        this.adres = checkNotNull(adres, "20190606182623");
    }

    @PrePersist
    public void prePersist() {
        fullTextSearch = normalizePacjent();
    }

    @PreUpdate
    public void preUpdate() {
        fullTextSearch = normalizePacjent();
    }

    private String normalizePacjent() {
        return normalize(newArrayList(numerKartoteki, pesel), pairOf(imie, nazwisko));
    }

}
