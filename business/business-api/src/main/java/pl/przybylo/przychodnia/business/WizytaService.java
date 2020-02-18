package pl.przybylo.przychodnia.business;

import pl.przybylo.przychodnia.dto.wizyta.*;

import java.time.LocalDate;
import java.util.List;

public interface WizytaService {

    List<WizytaViewDto> getWizytaList(long pacjentId);

    WizytaViewDto getWizyta(long id);

    WizytaViewDto update(WizytaEditDto wizytaEditDto);

    WizytaViewDto zaplanuj(ZaplanujWizyteDto zaplanujWizyteDto);

    WizytaViewDto zakoncz(ZakonczWizyteDto zakonczWizyteDto);

    void delete(long id);

    List<HarmonogramZaplanowanaWizytaDto> getZaplanowanaWizytaNaTydzienList(LocalDate dateFrom, LocalDate dateTo, long lekarzId, long specjalizacjaId);

}
