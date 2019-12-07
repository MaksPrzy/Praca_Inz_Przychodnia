package pl.przybylo.przychodnia.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Gabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    @Column(columnDefinition = "text")
    private String opis;

    private int pietro;

    public Gabinet(String nazwa, String opis, int pietro) {
        checkArgument(isNotBlank(nazwa), "20190606181044");
        checkArgument(isNotBlank(opis), "20190605181829");
        checkNotNull(pietro, "20190609214303");

        this.nazwa = checkNotNull(nazwa, "20191015151608");
        this.opis = opis;
        this.pietro = checkNotNull(pietro,"20190609210644");
    }

}
