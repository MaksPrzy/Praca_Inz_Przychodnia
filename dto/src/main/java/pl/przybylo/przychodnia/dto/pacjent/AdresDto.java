package pl.przybylo.przychodnia.dto.pacjent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdresDto {

    private String kodPocztowy;
    private String miejscowosc;
    private String ulica;
    private String numerDomu;

}
