package pl.przybylo.przychodnia.business;

import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramEditDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramNewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramViewDto;

import java.util.List;

public interface HarmonogramService {

    List<HarmonogramViewDto> getHarmonogramList(long lekarzId);

    HarmonogramViewDto add(HarmonogramNewDto harmonogramNewDto);

    HarmonogramViewDto update(HarmonogramEditDto harmonogramEditDto);

    void delete(long id);

    void publish(long id);

}
