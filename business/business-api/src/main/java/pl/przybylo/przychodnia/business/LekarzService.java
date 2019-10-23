package pl.przybylo.przychodnia.business;

import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzEditDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzNewDto;

import java.util.List;

public interface LekarzService {

    List<LekarzDetailViewDto> getLekarzList();

    LekarzDetailViewDto add(LekarzNewDto lekarzNewDto);

    LekarzDetailViewDto update(LekarzEditDto lekarzEditDto);

}
