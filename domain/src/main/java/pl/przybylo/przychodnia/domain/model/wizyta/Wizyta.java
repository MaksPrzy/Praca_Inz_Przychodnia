package pl.przybylo.przychodnia.domain.model.wizyta;

import lombok.Getter;
import lombok.Setter;
import pl.przybylo.przychodnia.domain.model.Gabinet;
import pl.przybylo.przychodnia.domain.model.Lekarz;
import pl.przybylo.przychodnia.domain.model.Pacjent;
import pl.przybylo.przychodnia.domain.model.Specjalizacja;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.google.common.collect.Lists.newArrayList;
import static pl.przybylo.przychodnia.commons.normalize.NormalizePair.pairOf;
import static pl.przybylo.przychodnia.commons.normalize.NormalizeUtils.normalize;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Entity
@Getter
@Setter
public class Wizyta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Pacjent pacjent;

    @OneToOne
    private Lekarz lekarz;

    @OneToOne
    private Specjalizacja specjalizacja;

    @OneToOne
    @JoinColumn(name = "gabinet_id", nullable = false)
    private Gabinet gabinet;

    private LocalDateTime dataWizytyOd;
    private LocalDateTime dataWizytyDo;
    private LocalDateTime faktycznaDataWizytyDo;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Rodzaj rodzaj;

    @Embedded
    private Rozpoznanie rozpoznanie;

    @Column(columnDefinition = "text")
    private String fullTextSearch;

    public Wizyta() {
        this.status = Status.ZAPLANOWANA;
    }

    public Wizyta(Pacjent pacjent, Lekarz lekarz, Specjalizacja specjalizacja, Gabinet gabinet,
                  LocalDateTime dataWizytyOd, LocalDateTime dataWizytyDo, Rodzaj rodzaj) {
        this();
        this.pacjent = checkNotNull(pacjent, "20191015180101");
        this.lekarz = checkNotNull(lekarz, "20191015180128");
        this.specjalizacja = checkNotNull(specjalizacja, "20191015180135");
        this.gabinet = checkNotNull(gabinet, "20191015180142");
        this.dataWizytyOd = checkNotNull(dataWizytyOd, "20191015180148");
        this.dataWizytyDo = checkNotNull(dataWizytyDo, "20191015180154");
        this.rodzaj = checkNotNull(rodzaj, "20191015180210");
    }

    @PrePersist
    public void prePersit() {
        fullTextSearch = normalizeWizyta();
    }

    @PreUpdate
    public void preUpdate() {
        fullTextSearch = normalizeWizyta();
    }

    public boolean isZaplanowana() {
        return status == Status.ZAPLANOWANA;
    }

    private String normalizeWizyta() {
        return normalize(newArrayList(pacjent.getPesel()), pairOf(pacjent.getImie(), pacjent.getNazwisko()),
                pairOf(lekarz.getImie(), lekarz.getNazwisko()));
    }

}
