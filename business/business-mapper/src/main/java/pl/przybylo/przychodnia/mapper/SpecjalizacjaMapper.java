package pl.przybylo.przychodnia.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.SpecjalizacjaNotFoundException;
import pl.przybylo.przychodnia.domain.model.LekarzSpecjalizacja;
import pl.przybylo.przychodnia.domain.model.Specjalizacja;
import pl.przybylo.przychodnia.domain.repository.SpecjalizacjaRepository;
import pl.przybylo.przychodnia.dto.lekarz.AbstractLekarzSpecjalizacjaDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzSpecjalizacjaViewDto;
import pl.przybylo.przychodnia.dto.lekarz.SpecjalizacjaViewDto;

import java.util.Collection;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Component
@RequiredArgsConstructor
public class SpecjalizacjaMapper {

    private final SpecjalizacjaRepository specjalizacjaRepository;

    public Set<LekarzSpecjalizacja> mapToLekarzSpecjalizacjaSet(Collection<? extends AbstractLekarzSpecjalizacjaDto> specjalizacjaCollection) {
        if (isEmpty(specjalizacjaCollection)) {
            return newHashSet();
        }

        // sposob 1
//        Set<LekarzSpecjalizacja> lekarzSpecjalizacjaSet = newHashSet();
//
//        for (AbstractLekarzSpecjalizacjaDto lekarzSpecjalizacjaNewDto : specjalizacjaCollection) {
//            lekarzSpecjalizacjaSet.add(map(lekarzSpecjalizacjaNewDto));
//        }
//
//        return lekarzSpecjalizacjaSet;


        // sposob 2
        return specjalizacjaCollection.stream()
                .map(s -> map(s))
                .collect(toSet());
    }

    public LekarzSpecjalizacja map(AbstractLekarzSpecjalizacjaDto lekarzSpecjalizacjaNewDto) {
        if (isNull(lekarzSpecjalizacjaNewDto)) {
            return null;
        }

        return new LekarzSpecjalizacja(
                map(lekarzSpecjalizacjaNewDto.getId()),
                lekarzSpecjalizacjaNewDto.getRokUzyskaniaDyplomuZeSpecjalizacji()
        );
    }

    public Set<LekarzSpecjalizacjaViewDto> map(Collection<LekarzSpecjalizacja> specjalizacjaCollection) {
        if (isEmpty(specjalizacjaCollection)) {
            return newHashSet();
        }

        return specjalizacjaCollection.stream()
                .map(s -> map(s))
                .collect(toSet());
    }

    public LekarzSpecjalizacjaViewDto map(LekarzSpecjalizacja lekarzSpecjalizacja) {
        if (isNull(lekarzSpecjalizacja)) {
            return null;
        }

        Specjalizacja specjalizacja = lekarzSpecjalizacja.getSpecjalizacja();

        return new LekarzSpecjalizacjaViewDto(
                specjalizacja.getId(),
                specjalizacja.getNazwa(),
                lekarzSpecjalizacja.getRokUzyskaniaDyplomuZeSpecjalizacji()
        );
    }

    public SpecjalizacjaViewDto map(Specjalizacja specjalizacja) {
        if (isNull(specjalizacja)) {
            return null;
        }

        return new SpecjalizacjaViewDto(
                specjalizacja.getId(),
                specjalizacja.getNazwa()
        );
    }

    private Specjalizacja map(long id) {
        return specjalizacjaRepository.findById(id).orElseThrow(() -> new SpecjalizacjaNotFoundException(id));
    }

}
