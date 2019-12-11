package pl.przybylo.przychodnia.dto.uzytkownik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZalogujDto {

    private String username;
    private String password;

}
