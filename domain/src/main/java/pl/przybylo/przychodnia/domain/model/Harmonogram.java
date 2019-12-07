package pl.przybylo.przychodnia.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Harmonogram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long specjalizacjaId;

    private LocalDateTime obowiazujeOd;

    private LocalDateTime obowiazujeDo;

    @OneToMany
    @JoinColumn(name = "harmonogram_id")
    private Set<HarmonogramPozycja> pozycjaCollection = new HashSet();

    private boolean aktywny = false;

    public Harmonogram(LocalDateTime obowiazujeOd, LocalDateTime obowiazujeDo, Set<HarmonogramPozycja> pozycjaCollection) {
        this.obowiazujeOd = checkNotNull(obowiazujeOd, "20190822185016");
        this.obowiazujeDo = checkNotNull(obowiazujeDo, "20190822185122");
        this.pozycjaCollection = checkNotNull(pozycjaCollection, "20190822185129");
    }

}
