package pl.przybylo.przychodnia.mapper.wizyta;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.GabinetNotFoundException;
import pl.przybylo.przychodnia.commons.exceptions.LekarzNotFoundException;
import pl.przybylo.przychodnia.commons.exceptions.PacjentNotFoundException;
import pl.przybylo.przychodnia.commons.exceptions.SpecjalizacjaNotFoundException;
import pl.przybylo.przychodnia.domain.model.Gabinet;
import pl.przybylo.przychodnia.domain.model.Lekarz;
import pl.przybylo.przychodnia.domain.model.Pacjent;
import pl.przybylo.przychodnia.domain.model.Specjalizacja;
import pl.przybylo.przychodnia.domain.model.wizyta.Rozpoznanie;
import pl.przybylo.przychodnia.domain.model.wizyta.Wizyta;
import pl.przybylo.przychodnia.domain.repository.GabinetRepository;
import pl.przybylo.przychodnia.domain.repository.LekarzRepository;
import pl.przybylo.przychodnia.domain.repository.PacjentRepository;
import pl.przybylo.przychodnia.domain.repository.SpecjalizacjaRepository;
import pl.przybylo.przychodnia.dto.wizyta.*;
import pl.przybylo.przychodnia.mapper.GabinetMapper;
import pl.przybylo.przychodnia.mapper.LekarzMapper;
import pl.przybylo.przychodnia.mapper.PacjentMapper;
import pl.przybylo.przychodnia.mapper.SpecjalizacjaMapper;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class WizytaMapper {

    private final PacjentRepository pacjentRepository;
    private final LekarzRepository lekarzRepository;
    private final SpecjalizacjaRepository specjalizacjaRepository;
    private final GabinetRepository gabinetRepository;
    private final PacjentMapper pacjentMapper;
    private final LekarzMapper lekarzMapper;
    private final SpecjalizacjaMapper specjalizacjaMapper;
    private final GabinetMapper gabinetMapper;
    private final RodzajMapper rodzajMapper;
    private final StatusMapper statusMapper;

    public List<WizytaViewDto> map(List<Wizyta> wizytaList) {
        if (CollectionUtils.isEmpty(wizytaList)) {
            return newArrayList();
        }

        return wizytaList.stream()
                .map(wizyta -> map(wizyta))
                .collect(toList());
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
                statusMapper.map(wizyta.getStatus()),
                rodzajMapper.map(wizyta.getRodzaj())
        );
    }

    public Wizyta map(ZaplanujWizyteDto zaplanujWizyteDto) {
        if (isNull(zaplanujWizyteDto)) {
            return null;
        }

        Pacjent pacjent = getPacjent(zaplanujWizyteDto.getPacjentId());
        Lekarz lekarz = getLekarz(zaplanujWizyteDto.getLekarzId());
        Specjalizacja specjalizacja = getSpecjalizacja(zaplanujWizyteDto.getSpecjalizacjaId());
        Gabinet gabinet = getGabinet(zaplanujWizyteDto.getGabinet());

        return new Wizyta(
                pacjent,
                lekarz,
                specjalizacja,
                gabinet,
                zaplanujWizyteDto.getDataWizytyOd(),
                zaplanujWizyteDto.getDataWizytyDo(),
                rodzajMapper.map(zaplanujWizyteDto.getRodzaj())
        );
    }

    public void map(Wizyta wizyta, WizytaEditDto wizytaEditDto) {
        checkNotNull(wizyta, "20191108184350");
        checkNotNull(wizytaEditDto, "20191108184358");

        Pacjent pacjent = getPacjent(wizytaEditDto.getPacjentId());
        Lekarz lekarz = getLekarz(wizytaEditDto.getLekarzId());
        Specjalizacja specjalizacja = getSpecjalizacja(wizytaEditDto.getSpecjalizacjaId());
        Gabinet gabinet = getGabinet(wizytaEditDto.getGabinet());

        wizyta.setPacjent(pacjent);
        wizyta.setLekarz(lekarz);
        wizyta.setSpecjalizacja(specjalizacja);
        wizyta.setGabinet(gabinet);
        wizyta.setDataWizytyOd(wizytaEditDto.getDataWizytyOd());
        wizyta.setDataWizytyDo(wizytaEditDto.getDataWizytyDo());
        wizyta.setRodzaj(rodzajMapper.map(wizytaEditDto.getRodzaj()));
        wizyta.setStatus(statusMapper.map(wizytaEditDto.getStatus()));
    }

    public void map(Wizyta wizyta, ZakonczWizyteDto zakonczWizyteDto) {
        wizyta.setRozpoznanie(new Rozpoznanie(zakonczWizyteDto.getKodIcd10(), zakonczWizyteDto.getUwagi()));
        wizyta.setFaktycznaDataWizytyDo(LocalDateTime.now());
    }

    public List<HarmonogramZaplanowanaWizytaDto> mapToHarmonogramZaplanowaWizytaDtoList(List<Wizyta> wizytaList) {
        if (CollectionUtils.isEmpty(wizytaList)) {
            return newArrayList();
        }

        return wizytaList.stream()
                .map(this::mapToHarmonogramZaplanowaWizytaDto)
                .collect(toList());
    }

    private HarmonogramZaplanowanaWizytaDto mapToHarmonogramZaplanowaWizytaDto(Wizyta wizyta) {
        if (isNull(wizyta)) {
            return null;
        }

        LocalDateTime dataWizytyOd = wizyta.getDataWizytyOd();
        LocalDateTime dataWizytyDo = wizyta.getDataWizytyDo();

        Function<LocalTime, Integer> toMinutes = time -> time.getHour() * 60 + time.getMinute();
        int minutesFrom = toMinutes.apply(dataWizytyOd.toLocalTime());
        int minutesTo = toMinutes.apply(dataWizytyDo.toLocalTime());

        return new HarmonogramZaplanowanaWizytaDto(dataWizytyOd.getDayOfWeek().getValue(), minutesFrom, minutesTo);
    }

    private Pacjent getPacjent(Long pacjentId) {
        return pacjentRepository.findById(pacjentId).
                orElseThrow(() -> new PacjentNotFoundException(pacjentId));
    }

    private Lekarz getLekarz(Long lekarzId) {
        return lekarzRepository.findById(lekarzId)
                .orElseThrow(() -> new LekarzNotFoundException(lekarzId));
    }

    private Specjalizacja getSpecjalizacja(Long specjalizacjaId) {
        return specjalizacjaRepository.findById(specjalizacjaId)
                .orElseThrow(() -> new SpecjalizacjaNotFoundException(specjalizacjaId));
    }

    private Gabinet getGabinet(Long gabinet) {
        return gabinetRepository.findById(gabinet)
                .orElseThrow(() -> new GabinetNotFoundException(gabinet));
    }

}
