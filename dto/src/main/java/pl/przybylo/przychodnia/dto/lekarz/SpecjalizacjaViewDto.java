package pl.przybylo.przychodnia.dto.lekarz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecjalizacjaViewDto {

    private Long id;
    private String nazwa;

}
