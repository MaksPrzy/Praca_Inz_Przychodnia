package pl.przybylo.przychodnia.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.GabinetNotFoundException;
import pl.przybylo.przychodnia.domain.model.Gabinet;
import pl.przybylo.przychodnia.domain.repository.GabinetRepository;
import pl.przybylo.przychodnia.dto.gabinet.GabinetEditDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetNewDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class GabinetMapper {

    private final GabinetRepository gabinetRepository;

    public Gabinet map(GabinetNewDto gabinetNewDto) {
        if (isNull(gabinetNewDto)) {
            return null;
        }

        return new Gabinet(
                gabinetNewDto.getNazwa(),
                gabinetNewDto.getOpis(),
                gabinetNewDto.getPietro()
        );
    }

    public void map(Gabinet gabinet, GabinetEditDto gabinetEditDto) {
        checkNotNull(gabinet, "20190725195248");
        checkNotNull(gabinetEditDto, "20190725195257");

        gabinet.setNazwa(gabinetEditDto.getNazwa());
        gabinet.setOpis(gabinetEditDto.getOpis());
        gabinet.setPietro(gabinetEditDto.getPietro());
    }

    public List<GabinetViewDto> map(List<Gabinet> gabinetList) {
        if (CollectionUtils.isEmpty(gabinetList)) {
            return newArrayList();
        }

        return gabinetList.stream()
                .map(gabinet -> map(gabinet))
                .collect(toList());
    }

    public GabinetViewDto map(Gabinet gabinet) {
        if (isNull(gabinet)) {
            return null;
        }

        return new GabinetViewDto(
                gabinet.getId(),
                gabinet.getNazwa(),
                gabinet.getOpis(),
                gabinet.getPietro()
        );
    }

    public Gabinet map(Long id) {
        checkNotNull(id, "20190822183947");

        return gabinetRepository.findById(id)
                .orElseThrow(() -> new GabinetNotFoundException(id));
    }

}
