package pl.przybylo.przychodnia.mapper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.domain.model.Harmonogram;
import pl.przybylo.przychodnia.domain.model.HarmonogramPozycja;
import pl.przybylo.przychodnia.domain.model.Lekarz;
import pl.przybylo.przychodnia.dto.harmonogram.*;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class HarmonogramMapper {

    private final GabinetMapper gabinetMapper;

    public List<HarmonogramViewDto> mapToHarmonogramViewDtoList(Set<Harmonogram> harmonogramSet) {
        if (CollectionUtils.isEmpty(harmonogramSet)) {
            return newArrayList();
        }

        return harmonogramSet.stream()
                .map(h -> map(h))
                .collect(toList());
    }

    public HarmonogramViewDto map(Harmonogram harmonogram) {
        if (isNull(harmonogram)) {
            return null;
        }
        return new HarmonogramViewDto(
                harmonogram.getId(),
                harmonogram.getSpecjalizacjaId(),
                harmonogram.getObowiazujeOd(),
                harmonogram.getObowiazujeDo(),
                mapToHarmonogramPozycjaViewDto(harmonogram.getPozycjaCollection())
        );
    }

    private Set<HarmonogramPozycjaViewDto> mapToHarmonogramPozycjaViewDto(Set<HarmonogramPozycja> harmonogramPozycjaSet) {
        if (isEmpty(harmonogramPozycjaSet)) {
            return newHashSet();
        }

        return harmonogramPozycjaSet.stream()
                .map(p -> mapToHarmonogramPozycjaViewDto(p))
                .collect(toSet());
    }

    private HarmonogramPozycjaViewDto mapToHarmonogramPozycjaViewDto(HarmonogramPozycja harmonogramPozycja) {
        if (isNull(harmonogramPozycja)) {
            return null;
        }

        return new HarmonogramPozycjaViewDto(
                harmonogramPozycja.getGabinet().getId(),
                harmonogramPozycja.getDzienTygodnia(),
                harmonogramPozycja.getGodzinaOd(),
                harmonogramPozycja.getGodzinaDo(),
                harmonogramPozycja.getInterwalCzasowy()
        );
    }

    public Harmonogram map(HarmonogramNewDto harmonogramNewDto) {
        if (isNull(harmonogramNewDto)) {
            return null;
        }

        return new Harmonogram(
                harmonogramNewDto.getObowiazujeOd(),
                harmonogramNewDto.getObowiazujeDo(),
                map(harmonogramNewDto.getPozycjaCollection())
        );
    }

    public void map(Harmonogram harmonogram, HarmonogramEditDto harmonogramEditDto) {
        checkNotNull(harmonogram, "20190821212626");
        checkNotNull(harmonogramEditDto, "20190821212725");

        harmonogram.setObowiazujeOd(harmonogramEditDto.getObowiazujeOd());
        harmonogram.setObowiazujeDo(harmonogramEditDto.getObowiazujeDo());
        harmonogram.setPozycjaCollection(map(harmonogramEditDto.getPozycjaCollection()));
    }


    public HarmonogramEditDto map(Harmonogram harmonogram, Lekarz lekarz) {
        checkNotNull(lekarz, "20190822185836");

        if (isNull(harmonogram)) {
            return null;
        }

        return new HarmonogramEditDto(
                harmonogram.getId(),
                lekarz.getId(),
                harmonogram.getObowiazujeOd(),
                harmonogram.getObowiazujeDo(),
                mapHarmonogramPozycja(harmonogram.getPozycjaCollection()),
                false
        );
    }

    private Set<HarmonogramPozycjaEditDto> mapHarmonogramPozycja(Set<HarmonogramPozycja> harmonogramPozycjaSet) {
        if (isEmpty(harmonogramPozycjaSet)) {
            return newHashSet();
        }

        return harmonogramPozycjaSet.stream()
                .map(p -> map(p))
                .collect(toSet());
    }

    private HarmonogramPozycjaEditDto map(HarmonogramPozycja harmonogramPozycja) {
        if (isNull(harmonogramPozycja)) {
            return null;
        }

        return new HarmonogramPozycjaEditDto(
                harmonogramPozycja.getGabinet().getId(),
                harmonogramPozycja.getDzienTygodnia(),
                harmonogramPozycja.getGodzinaOd(),
                harmonogramPozycja.getGodzinaDo(),
                harmonogramPozycja.getInterwalCzasowy()
        );
    }

    private <T extends AbstractHarmonogramPozycjaDto> Set<HarmonogramPozycja> map(Set<T> harmonogramPozycjaDtoCollection) {
        if (isEmpty(harmonogramPozycjaDtoCollection)) {
            return newHashSet();
        }

        return harmonogramPozycjaDtoCollection.stream()
                .map(h -> map(h))
                .collect(toSet());
    }

    private <T extends AbstractHarmonogramPozycjaDto> HarmonogramPozycja map(T harmonogramPozycjaDto) {
        if (isNull(harmonogramPozycjaDto)) {
            return null;
        }

        return new HarmonogramPozycja(
                harmonogramPozycjaDto.getGodzinaOd(),
                harmonogramPozycjaDto.getGodzinaDo(),
                gabinetMapper.map(harmonogramPozycjaDto.getGabinetId())
        );
    }

}
