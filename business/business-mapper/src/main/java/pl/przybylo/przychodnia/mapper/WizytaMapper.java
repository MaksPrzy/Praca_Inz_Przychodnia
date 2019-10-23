package pl.przybylo.przychodnia.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.domain.model.wizyta.Wizyta;
import pl.przybylo.przychodnia.dto.wizyta.WizytaViewDto;

import java.util.List;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class WizytaMapper {

    private final PacjentMapper pacjentMapper;
    private final LekarzMapper lekarzMapper;
    private final SpecjalizacjaMapper specjalizacjaMapper;
    private final GabinetMapper gabinetMapper;

    public List<WizytaViewDto> map(List<Wizyta> wizytaList) {
        return null;
    }

    public WizytaViewDto map(Wizyta wizyta) {
        checkNotNull(wizyta, "20191015191210");

        return new WizytaViewDto(
                wizyta.getId(),
                pacjentMapper.map(wizyta.getPacjent()),
                lekarzMapper.map(wizyta.getLekarz()),
                specjalizacjaMapper.map(wizyta.getSpecjalizacja()),
                gabinetMapper.map(wizyta.getGabinet()),
                wizyta.getDataWizytyOd(),
                wizyta.getDataWizytyDo(),
                wizyta.getStatus().toString(),
                wizyta.getRodzaj().toString()
        );
    }

}
