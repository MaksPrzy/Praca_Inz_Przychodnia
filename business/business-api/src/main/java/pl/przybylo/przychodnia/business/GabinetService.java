package pl.przybylo.przychodnia.business;

import pl.przybylo.przychodnia.dto.gabinet.GabinetEditDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetNewDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;

import java.util.List;

public interface GabinetService {

    List<GabinetViewDto> getGabinetList();

    GabinetViewDto add(GabinetNewDto gabinetNewDto);

    GabinetViewDto update(GabinetEditDto gabinetEditDto);

}
