package pl.przybylo.przychodnia.business.impl.gabinet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.GabinetService;
import pl.przybylo.przychodnia.commons.exceptions.GabinetNotFoundException;
import pl.przybylo.przychodnia.domain.model.Gabinet;
import pl.przybylo.przychodnia.domain.repository.GabinetRepository;
import pl.przybylo.przychodnia.dto.gabinet.GabinetEditDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetNewDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;
import pl.przybylo.przychodnia.mapper.GabinetMapper;

import java.util.List;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Service
@RequiredArgsConstructor
public class GabinetServiceImpl implements GabinetService {

    private final GabinetValidator gabinetValidator;
    private final GabinetRepository gabinetRepository;
    private final GabinetMapper gabinetMapper;

    @Override
    public List<GabinetViewDto> getGabinetList() {
        List<Gabinet> gabinetList = gabinetRepository.findAll();
        return gabinetMapper.map(gabinetList);
    }

    @Override
    public GabinetViewDto add(GabinetNewDto gabinetNewDto) {
        checkNotNull(gabinetNewDto, "20190724233236");

        gabinetValidator.check(gabinetNewDto);

        Gabinet newGabinet = gabinetRepository.save(gabinetMapper.map(gabinetNewDto));

        return gabinetMapper.map(newGabinet);
    }

    @Override
    public GabinetViewDto update(GabinetEditDto gabinetEditDto) {
        checkNotNull(gabinetEditDto, "20190725194615");
        checkNotNull(gabinetEditDto.getId(), "20190725194806");

        gabinetValidator.check(gabinetEditDto);

        Long gabinetId = gabinetEditDto.getId();
        Gabinet gabinet = gabinetRepository.findById(gabinetId).orElseThrow(() -> new GabinetNotFoundException(gabinetId));
        gabinetMapper.map(gabinet, gabinetEditDto);
        gabinet = gabinetRepository.save(gabinet);

        return gabinetMapper.map(gabinet);
    }

}
