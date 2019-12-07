package pl.przybylo.przychodnia.business;

import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentEditDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentNewDto;

import java.util.List;

public interface PacjentService {

    List<PacjentDetailViewDto> getPacjentList();

    PacjentDetailViewDto getPacjent(Long id);

    PacjentDetailViewDto add(PacjentNewDto pacjentNewDto);

    PacjentDetailViewDto update(PacjentEditDto pacjentEditDto);

}
