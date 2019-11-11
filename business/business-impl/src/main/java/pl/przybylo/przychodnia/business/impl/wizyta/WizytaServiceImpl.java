package pl.przybylo.przychodnia.business.impl.wizyta;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.WizytaService;
import pl.przybylo.przychodnia.commons.exceptions.WizytaNotFoundException;
import pl.przybylo.przychodnia.domain.model.wizyta.Wizyta;
import pl.przybylo.przychodnia.domain.repository.WizytaRepository;
import pl.przybylo.przychodnia.dto.wizyta.WizytaEditDto;
import pl.przybylo.przychodnia.dto.wizyta.WizytaViewDto;
import pl.przybylo.przychodnia.dto.wizyta.ZakonczWizyteDto;
import pl.przybylo.przychodnia.dto.wizyta.ZaplanujWizyteDto;
import pl.przybylo.przychodnia.mapper.wizyta.WizytaMapper;

import java.time.LocalDateTime;
import java.util.List;

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
        Wizyta wizyta = getWizytaOrThrowException(id);
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
        Wizyta wizyta = getWizytaOrThrowException(zakonczWizyteDto.getWizytaId());
        wizyta.setFaktycznaDataWizytyDo(LocalDateTime.now());
        return null;
    }

    @Override
    public void delete(long id) {
        Wizyta wizyta = getWizytaOrThrowException(id);
//        if () {
//            wizytaRepository.deleteById(id);
//        }
    }

    private Wizyta getWizytaOrThrowException(long id) {
        return wizytaRepository.findById(id).orElseThrow(() -> new WizytaNotFoundException(id));
    }

}
