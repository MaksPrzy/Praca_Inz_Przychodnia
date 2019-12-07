package pl.przybylo.przychodnia.business;

import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.dto.wizyta.WizytaEditDto;
import pl.przybylo.przychodnia.dto.wizyta.WizytaViewDto;
import pl.przybylo.przychodnia.dto.wizyta.ZakonczWizyteDto;
import pl.przybylo.przychodnia.dto.wizyta.ZaplanujWizyteDto;

import java.util.List;

public interface WizytaService {

    List<WizytaViewDto> getWizytaList(long pacjentId);

    WizytaViewDto getWizyta(long id);

    WizytaViewDto update(WizytaEditDto wizytaEditDto);

    WizytaViewDto zaplanuj(ZaplanujWizyteDto zaplanujWizyteDto);

    WizytaViewDto zakoncz(ZakonczWizyteDto zakonczWizyteDto);

    void delete(long id);

}
