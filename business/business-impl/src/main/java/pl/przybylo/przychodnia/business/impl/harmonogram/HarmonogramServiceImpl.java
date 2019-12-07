package pl.przybylo.przychodnia.business.impl.harmonogram;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.HarmonogramService;
import pl.przybylo.przychodnia.commons.exceptions.CantDeleteActiveHarmonogramException;
import pl.przybylo.przychodnia.commons.exceptions.HarmonogramIsAlreadyActiveException;
import pl.przybylo.przychodnia.commons.exceptions.HarmonogramNotFoundException;
import pl.przybylo.przychodnia.commons.exceptions.LekarzNotFoundException;
import pl.przybylo.przychodnia.domain.model.Harmonogram;
import pl.przybylo.przychodnia.domain.model.Lekarz;
import pl.przybylo.przychodnia.domain.repository.HarmonogramRepository;
import pl.przybylo.przychodnia.domain.repository.LekarzRepository;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramEditDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramNewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramViewDto;
import pl.przybylo.przychodnia.mapper.HarmonogramMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HarmonogramServiceImpl implements HarmonogramService {

    private final LekarzRepository lekarzRepository;
    private final HarmonogramRepository harmonogramRepository;
    private final HarmonogramMapper harmonogramMapper;
    private final HarmonogramValidator harmonogramValidator;

    @Override
    public List<HarmonogramViewDto> getHarmonogramList(long lekarzId) {
        Lekarz lekarz = lekarzRepository.findById(lekarzId).orElseThrow(() -> new LekarzNotFoundException(lekarzId));
        return harmonogramMapper.mapToHarmonogramViewDtoList(lekarz.getHarmonogramCollection());
    }

    @Override
    public HarmonogramViewDto add(HarmonogramNewDto harmonogramNewDto) {
        harmonogramValidator.check(harmonogramNewDto);

        Harmonogram harmonogram = harmonogramMapper.map(harmonogramNewDto);
        Harmonogram newHarmonogram = harmonogramRepository.save(harmonogram);
        return harmonogramMapper.map(newHarmonogram);
    }

    @Override
    public HarmonogramViewDto update(HarmonogramEditDto harmonogramEditDto) {
        harmonogramValidator.check(harmonogramEditDto);

        Harmonogram harmonogram = getHarmonogramOrThrowException(harmonogramEditDto.getId());
        harmonogramMapper.map(harmonogram, harmonogramEditDto);
        harmonogram = harmonogramRepository.save(harmonogram);

        return harmonogramMapper.map(harmonogram);
    }

    @Override
    public void delete(long id) {
        Harmonogram harmonogram = getHarmonogramOrThrowException(id);

        if (harmonogram.isAktywny()) {
            throw new CantDeleteActiveHarmonogramException();
        }

        harmonogramRepository.deleteById(id);
    }

    @Override
    public void publish(long id) {
        Harmonogram harmonogram = getHarmonogramOrThrowException(id);

        if (harmonogram.isAktywny()) {
            throw new HarmonogramIsAlreadyActiveException();
        }

        harmonogram.setAktywny(true);
        harmonogramRepository.save(harmonogram);
    }

    private Harmonogram getHarmonogramOrThrowException(long id) {
        return harmonogramRepository.findById(id).orElseThrow(() -> new HarmonogramNotFoundException(id));
    }

}
