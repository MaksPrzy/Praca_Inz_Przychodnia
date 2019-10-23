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
    private final KontaktMapper kontaktMapper;

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
                adresMapper.map(pacjentNewDto.getAdres()),
                kontaktMapper.map(pacjentNewDto.getKontakt())
        );
    }

    public Pacjent mapToEdit(String numerKartoteki, PacjentEditDto pacjentEditDto) {
        if (isNull(pacjentEditDto)) {
            return null;
        }

        return new Pacjent(
                numerKartoteki,
                pacjentEditDto.getPesel(),
                pacjentEditDto.getImie(),
                pacjentEditDto.getNazwisko(),
                pacjentEditDto.getDataUrodzenia(),
                adresMapper.map(pacjentEditDto.getAdres()),
                kontaktMapper.map(pacjentEditDto.getKontakt())
        );
    }

    public PacjentDetailViewDto map(Pacjent pacjent) {
        checkNotNull(pacjent, "20191015182847");

        return new PacjentDetailViewDto(
                pacjent.getId(),
                pacjent.getNumerKartoteki(),
                pacjent.getImie(),
                pacjent.getNazwisko(),
                pacjent.getPesel(),
                pacjent.getDataUrodzenia(),
                adresMapper.map(pacjent.getAdres()),
                kontaktMapper.map(pacjent.getKontakt())
        );
    }

}
