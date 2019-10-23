package pl.przybylo.przychodnia.dto.pacjent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KontaktDto {

    private String email;
    private String telefonKomorkowy;
    private String telefonStacjonarny;

}
