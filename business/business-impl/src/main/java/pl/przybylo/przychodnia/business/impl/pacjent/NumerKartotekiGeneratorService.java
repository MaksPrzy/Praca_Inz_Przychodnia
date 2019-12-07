package pl.przybylo.przychodnia.business.impl.pacjent;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.dto.pacjent.PacjentNewDto;

@Service
public class NumerKartotekiGeneratorService {

    public String generate(PacjentNewDto pacjentNewDto) {
        String imie = pacjentNewDto.getImie();
        String nazwisko = pacjentNewDto.getNazwisko();
        String randomNo = RandomStringUtils.random(5, false, true);

        StringBuilder numerKaroteki = new StringBuilder();
        numerKaroteki.append(imie.charAt(0)).append(nazwisko.charAt(0)).append(randomNo);

        return numerKaroteki.toString().toUpperCase();
    }

}
