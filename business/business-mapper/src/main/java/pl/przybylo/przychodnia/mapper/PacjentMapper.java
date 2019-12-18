package pl.przybylo.przychodnia.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.domain.model.Pacjent;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentEditDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentNewDto;

import static java.util.Objects.isNull;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class PacjentMapper {

    private final AdresMapper adresMapper;

    public Pacjent map(String numerKartoteki, PacjentNewDto pacjentNewDto) {
        if (isNull(pacjentNewDto)) {
            return null;
        }

        return new Pacjent(
                numerKartoteki,
                pacjentNewDto.getPesel(),
                pacjentNewDto.getImie(),
                pacjentNewDto.getNazwisko(),
                pacjentNewDto.getDataUrodzenia(),
                pacjentNewDto.getEmail(),
                pacjentNewDto.getHaslo(),
                pacjentNewDto.getTelefonKomorkowy(),
                adresMapper.map(pacjentNewDto.getAdres())
        );
    }

    public void mapToEdit(PacjentEditDto pacjentEditDto) {
        checkNotNull(pacjentEditDto, "20191111160155");

        pacjentEditDto.setImie(pacjentEditDto.getImie());
        pacjentEditDto.setNazwisko(pacjentEditDto.getNazwisko());
        pacjentEditDto.setPesel(pacjentEditDto.getPesel());
        pacjentEditDto.setDataUrodzenia(pacjentEditDto.getDataUrodzenia());
        adresMapper.map(pacjentEditDto.getAdres());

    }


    public PacjentDetailViewDto map(Pacjent pacjent) {
        if (isNull(pacjent)) {
            return null;
        }

        return new PacjentDetailViewDto(
                pacjent.getId(),
                pacjent.getNumerKartoteki(),
                pacjent.getImie(),
                pacjent.getNazwisko(),
                pacjent.getPesel(),
                pacjent.getDataUrodzenia(),
                pacjent.getEmail(),
                pacjent.getHaslo(),
                pacjent.getTelefonKomorkowy(),
                adresMapper.map(pacjent.getAdres())
        );
    }

}
