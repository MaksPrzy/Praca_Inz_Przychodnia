package pl.przybylo.przychodnia.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.przybylo.przychodnia.domain.model.Lekarz;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzEditDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzNewDto;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class LekarzMapper {

    private final SpecjalizacjaMapper specjalizacjaMapper;

    public Lekarz map(LekarzNewDto lekarzNewDto) {
        if (isNull(lekarzNewDto)) {
            return null;
        }

        return new Lekarz(
                lekarzNewDto.getImie(),
                lekarzNewDto.getNazwisko(),
                lekarzNewDto.getNumer(),
                specjalizacjaMapper.mapToLekarzSpecjalizacjaSet(lekarzNewDto.getSpecjalizacjaCollection())
        );
    }

    public void map(Lekarz lekarz, LekarzEditDto lekarzEditDto) {
        checkNotNull(lekarz, "20190821185510");
        checkNotNull(lekarzEditDto, "20190821185528");

        lekarz.setImie(lekarzEditDto.getImie());
        lekarz.setNazwisko(lekarzEditDto.getNazwisko());
        lekarz.setNumer(lekarzEditDto.getNumer());
        lekarz.setSpecjalizacjaCollection(specjalizacjaMapper.mapToLekarzSpecjalizacjaSet(lekarzEditDto.getSpecjalizacjaCollection()));
    }

    public List<LekarzDetailViewDto> map(List<Lekarz> lekarzList) {
        if (CollectionUtils.isEmpty(lekarzList)) {
            return newArrayList();
        }

        return lekarzList.stream()
                .map(lekarz -> map(lekarz))
                .collect(toList());
    }

    public LekarzDetailViewDto map(Lekarz lekarz) {
        if (isNull(lekarz)) {
            return null;
        }

        return new LekarzDetailViewDto(
                lekarz.getId(),
                lekarz.getImie(),
                lekarz.getNazwisko(),
                lekarz.getNumer(),
                specjalizacjaMapper.map(lekarz.getSpecjalizacjaCollection())
        );
    }

}
