package pl.przybylo.przychodnia.mapper;

import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.domain.model.Adres;
import pl.przybylo.przychodnia.dto.pacjent.AdresDto;

import static java.util.Objects.isNull;

@Component
public class AdresMapper {

    public Adres map(AdresDto adresDto) {
        if (isNull(adresDto)) {
            return null;
        }

        return new Adres(
                adresDto.getKodPocztowy(),
                adresDto.getMiejscowosc(),
                adresDto.getUlica(),
                adresDto.getNumerDomu(),
                adresDto.getNumerLokalu()
        );
    }

    public AdresDto map(Adres adres) {
        if (isNull(adres)) {
            return null;
        }

        return new AdresDto(
                adres.getKodPocztowy(),
                adres.getMiejscowosc(),
                adres.getUlica(),
                adres.getNumerDomu(),
                adres.getNumerLokalu()
        );
    }

}
