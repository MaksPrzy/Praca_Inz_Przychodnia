package pl.przybylo.przychodnia.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class HarmonogramPozycja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dzienTygodnia;

    private LocalTime godzinaOd;

    private LocalTime godzinaDo;

    private int interwalCzasowy; // wyrazony w minutach

    @OneToOne
    @JoinColumn(name = "gabinet_id", nullable = false)
    private Gabinet gabinet;

    public HarmonogramPozycja(LocalTime godzinaOd, LocalTime godzinaDo, Gabinet gabinet) {
        checkNotNull(godzinaOd, "20190609214816");
        checkNotNull(godzinaDo, "20190609215002");
        checkNotNull(gabinet, "20190609215311");

        this.godzinaOd = godzinaOd;
        this.godzinaDo = godzinaDo;
        this.gabinet = gabinet;
    }

}
