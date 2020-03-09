package pl.przybylo.przychodnia.business.impl.wizyta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.WizytaService;
import pl.przybylo.przychodnia.commons.exceptions.CantDeleteWizytaException;
import pl.przybylo.przychodnia.commons.exceptions.WizytaNotFoundException;
import pl.przybylo.przychodnia.domain.model.wizyta.Wizyta;
import pl.przybylo.przychodnia.domain.repository.WizytaRepository;
import pl.przybylo.przychodnia.dto.wizyta.*;
import pl.przybylo.przychodnia.mapper.wizyta.WizytaMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Service
@RequiredArgsConstructor
public class WizytaServiceImpl implements WizytaService {

    private final WizytaRepository wizytaRepository;
    private final WizytaMapper wizytaMapper;
    private final WizytaValidator wizytaValidator;

    @Override
    public List<WizytaViewDto> getWizytaList(long pacjentId) {
        List<Wizyta> wizytaList = wizytaRepository.findByPacjentId(pacjentId);
        return wizytaMapper.map(wizytaList);
    }

    @Override
    public WizytaViewDto getWizyta(long id) {
        Wizyta wizyta = wizytaRepository.findByIdOrThrowException(id);
        return wizytaMapper.map(wizyta);
    }

    @Override
    public WizytaViewDto update(WizytaEditDto wizytaEditDto) {
        checkNotNull(wizytaEditDto, "20191026150607");
        checkNotNull(wizytaEditDto.getId(), "20191101173728");

        wizytaValidator.checkUpdate(wizytaEditDto);

        Long wizytaId = wizytaEditDto.getId();
        Wizyta wizyta = wizytaRepository.findById(wizytaId).orElseThrow(() -> new WizytaNotFoundException(wizytaId));
        wizytaMapper.map(wizyta, wizytaEditDto);
        wizyta = wizytaRepository.save(wizyta);

        return wizytaMapper.map(wizyta);
    }

    @Override
    public WizytaViewDto zaplanuj(ZaplanujWizyteDto zaplanujWizyteDto) {
        Wizyta wizyta = wizytaRepository.save(wizytaMapper.map(zaplanujWizyteDto));
        return wizytaMapper.map(wizyta);
    }

    @Override
    public WizytaViewDto zakoncz(ZakonczWizyteDto zakonczWizyteDto) {
        Wizyta wizyta = wizytaRepository.findByIdOrThrowException(zakonczWizyteDto.getWizytaId());
        wizytaMapper.map(wizyta, zakonczWizyteDto);
        wizyta = wizytaRepository.save(wizyta);
        return wizytaMapper.map(wizyta);
    }

    @Override
    public void delete(long id) {
        Wizyta wizyta = wizytaRepository.findByIdOrThrowException(id);

        if (wizyta.isZaplanowana()) {
            wizytaRepository.deleteById(id);
        } else {
            throw new CantDeleteWizytaException(id);
        }
    }

    @Override
    public List<HarmonogramZaplanowanaWizytaDto> getZaplanowanaWizytaNaTydzienList(LocalDate poczatekTygodnia, LocalDate koniecTygodnia,
                                                                                   long lekarzId, long specjalizacjaId) {
        checkNotNull(poczatekTygodnia, "20200213200608");
        checkNotNull(koniecTygodnia, "20200213200613");

        LocalDateTime dataWizytyOd = poczatekTygodnia.atStartOfDay();
        LocalDateTime dataWizytyDo = koniecTygodnia.atTime(LocalTime.MAX);

        List<Wizyta> wizytaList = wizytaRepository.findByLekarzIdAndSpecjalizacjaId(lekarzId, specjalizacjaId).stream()
                .filter(wizyta -> !wizyta.isZakonczona())
                .filter(wizyta -> wizyta.getDataWizytyOd().isAfter(dataWizytyOd))
                .filter(wizyta -> wizyta.getDataWizytyDo().isBefore(dataWizytyDo))
                .collect(Collectors.toList());

        return wizytaMapper.mapToHarmonogramZaplanowaWizytaDtoList(wizytaList);
    }

}
