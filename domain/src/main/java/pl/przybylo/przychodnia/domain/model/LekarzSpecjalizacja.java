package pl.przybylo.przychodnia.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class LekarzSpecjalizacja {

    private static final int MIN_ROK_UZYSKANIA_DYPLOMU_ZE_SPECJALIZACJI = 1950;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Specjalizacja specjalizacja;

    private int rokUzyskaniaDyplomuZeSpecjalizacji;

    public LekarzSpecjalizacja(Specjalizacja specjalizacja, int rokUzyskaniaDyplomuZeSpecjalizacji) {
        checkNotNull(specjalizacja, "20190712202610");
        checkArgument(rokUzyskaniaDyplomuZeSpecjalizacji >= MIN_ROK_UZYSKANIA_DYPLOMU_ZE_SPECJALIZACJI, "20190712202619");

        this.specjalizacja = specjalizacja;
        this.rokUzyskaniaDyplomuZeSpecjalizacji = rokUzyskaniaDyplomuZeSpecjalizacji;
    }

    public String getSpecjalizacjaNormalized() {
        return specjalizacja.normalizeSpecjalizacja();
    }

}
