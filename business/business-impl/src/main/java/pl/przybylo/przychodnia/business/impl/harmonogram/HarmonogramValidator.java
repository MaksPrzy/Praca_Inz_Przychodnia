package pl.przybylo.przychodnia.business.impl.harmonogram;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.commons.exceptions.*;
import pl.przybylo.przychodnia.domain.model.Harmonogram;
import pl.przybylo.przychodnia.domain.model.HarmonogramPozycja;
import pl.przybylo.przychodnia.domain.model.Lekarz;
import pl.przybylo.przychodnia.domain.repository.LekarzRepository;
import pl.przybylo.przychodnia.dto.harmonogram.AbstractHarmonogramPozycjaDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramEditDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramNewDto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toSet;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.fieldRequired;
import static pl.przybylo.przychodnia.commons.exceptions.FieldError.of;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
@RequiredArgsConstructor
public class HarmonogramValidator {

    private static final String OBOWIAZUJE_OD_FIELD_NAME = "obowiazujeOd";

    private final LekarzRepository lekarzRepository;

    public void check(HarmonogramNewDto harmonogramNewDto) {
        checkNotNull(harmonogramNewDto, "20190905191421");

        check(
                harmonogramNewDto.getLekarzId(),
                harmonogramNewDto.getObowiazujeOd(),
                harmonogramNewDto.getObowiazujeDo(),
                harmonogramNewDto.getPozycjaCollection(),
                harmonogramNewDto.isShouldCheckDatesImpose()
        );
    }

    public void check(HarmonogramEditDto harmonogramEditDto) {
        checkNotNull(harmonogramEditDto, "20190905191457");

        check(
                harmonogramEditDto.getLekarzId(),
                harmonogramEditDto.getObowiazujeOd(),
                harmonogramEditDto.getObowiazujeDo(),
                harmonogramEditDto.getPozycjaCollection(),
                harmonogramEditDto.isShouldCheckDatesImpose()
        );
    }

    private void check(Long lekarzId, LocalDateTime obowiazujeOd, LocalDateTime obowiazujeDo,
                       Set<? extends AbstractHarmonogramPozycjaDto> pozycjaCollection, boolean shouldCheckDatesImpose) {
        Set<FieldError> fieldErrorSet = newHashSet();

        if (isNull(lekarzId)) {
            fieldErrorSet.add(fieldRequired("lekarz"));
        }

        if (isNull(obowiazujeOd)) {
            fieldErrorSet.add(fieldRequired(OBOWIAZUJE_OD_FIELD_NAME));
        }

        if (isNull(obowiazujeDo)) {
            fieldErrorSet.add(fieldRequired("obowiazujeDo"));
        }

        if (obowiazujeOd.isAfter(obowiazujeDo)) {
            fieldErrorSet.add(of(OBOWIAZUJE_OD_FIELD_NAME, "Data 'od' nie powinna być późniejsza niż data 'do'."));
        }

        if (CollectionUtils.isEmpty(pozycjaCollection)) {
            fieldErrorSet.add(fieldRequired("pozycja"));
        } else {
            Set<HarmonogramSingleDay> harmonogramSingleDaySet = from(pozycjaCollection);
            Lekarz lekarz = lekarzRepository.findById(lekarzId).orElseThrow(() -> new LekarzNotFoundException(lekarzId));

            LocalDateTime now = LocalDateTime.now();

            // sprawdzenie czy daty sie nakladaja
            if (shouldCheckDatesImpose) {
                checkDatesNotImpose(harmonogramSingleDaySet, lekarz.getHarmonogramCollection().stream()
                        .filter(h -> h.isAktywny())
                        .filter(h -> h.getObowiazujeDo().isAfter(now))
                        .collect(toSet()));
            }
        }

        if (!CollectionUtils.isEmpty(fieldErrorSet)) {
            throw new AppFieldException(fieldErrorSet);
        }
    }

    private void checkDatesNotImpose(Set<HarmonogramSingleDay> harmonogramSingleDaySet, Set<Harmonogram> harmonogramSet) {
        for (Harmonogram harmonogram : harmonogramSet) {
            Set<HarmonogramPozycja> pozycjaCollection = harmonogram.getPozycjaCollection();

            for (HarmonogramPozycja pozycja : pozycjaCollection) {
                for (HarmonogramSingleDay harmonogramSingleDay : harmonogramSingleDaySet) {
                    if (harmonogramSingleDay.isImpose(pozycja)) {
                        throw new HarmonogramImposeWithAnotherHarmonogramException();
                    }
                }
            }
        }
    }

    private Set<HarmonogramSingleDay> from(Set<? extends AbstractHarmonogramPozycjaDto> harmonogramPozycjaNewDtoSet) {
        return harmonogramPozycjaNewDtoSet.stream()
                .map(h -> from(h))
                .collect(toSet());
    }

    private HarmonogramSingleDay from(AbstractHarmonogramPozycjaDto harmonogramPozycjaNewDto) {
        return new HarmonogramSingleDay(
                harmonogramPozycjaNewDto.getDzienTygodnia(),
                harmonogramPozycjaNewDto.getGodzinaOd(),
                harmonogramPozycjaNewDto.getGodzinaDo()
        );
    }

}

@AllArgsConstructor
class HarmonogramSingleDay {

    private int dzienTygodnia;
    private LocalTime godzinaOd;
    private LocalTime godzinaDo;

    public boolean isImpose(HarmonogramPozycja harmonogramPozycja) {
        return dzienTygodnia == harmonogramPozycja.getDzienTygodnia() && (
                    !harmonogramPozycja.getGodzinaOd().isBefore(godzinaDo) ||
                    godzinaOd.isBefore(harmonogramPozycja.getGodzinaDo())
                );
    }

}
