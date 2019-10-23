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
import pl.przybylo.przychodnia.mapper.WizytaMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WizytaServiceImpl implements WizytaService {

    private final WizytaRepository wizytaRepository;
    private final WizytaMapper wizytaMapper;

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
        Wizyta wizyta = getWizytaOrThrowException(wizytaEditDto.getId());

        return null;
    }

    @Override
    public WizytaViewDto zaplanuj(ZaplanujWizyteDto zaplanujWizyteDto) {
        throw new UnsupportedOperationException("nie zrobione!");
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

//        if (wizyta.) {
//            throw new CantDeleteActiveHarmonogramException();   //todo wiem ze trzeba sie odniesc do enuma statusu ale jak ?
//        }

        wizytaRepository.deleteById(id);
    }

    private Wizyta getWizytaOrThrowException(long id) {
        return wizytaRepository.findById(id).orElseThrow(() -> new WizytaNotFoundException(id));
    }

}
