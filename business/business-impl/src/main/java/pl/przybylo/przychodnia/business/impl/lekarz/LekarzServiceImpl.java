package pl.przybylo.przychodnia.business.impl.lekarz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.LekarzService;
import pl.przybylo.przychodnia.commons.exceptions.LekarzNotFoundException;
import pl.przybylo.przychodnia.domain.model.Lekarz;
import pl.przybylo.przychodnia.domain.repository.LekarzRepository;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzEditDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzNewDto;
import pl.przybylo.przychodnia.mapper.LekarzMapper;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.przybylo.przychodnia.commons.normalize.NormalizeUtils.normalize;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Service
@RequiredArgsConstructor
public class LekarzServiceImpl implements LekarzService {

    private final LekarzRepository lekarzRepository;
    private final LekarzValidator lekarzValidator;
    private final LekarzMapper lekarzMapper;

    @Override
    public List<LekarzDetailViewDto> getLekarzList(String searchBy) {
        List<Lekarz> lekarzList;

        if (isNotBlank(searchBy)) {
            lekarzList = lekarzRepository.findByFullTextSearchContains(normalize(searchBy));
        } else {
            lekarzList = lekarzRepository.findAll();
        }

        return lekarzMapper.map(lekarzList);
    }

    @Override
    public LekarzDetailViewDto getLekarz(Long lekarzId) {
        checkNotNull(lekarzId, "20200213193433");

        Lekarz lekarz = findLekarzByIdOrThrowException(lekarzId);
        return lekarzMapper.map(lekarz);
    }

    @Override
    public LekarzDetailViewDto add(LekarzNewDto lekarzNewDto) {
        checkNotNull(lekarzNewDto, "20190712170210");

        lekarzValidator.check(lekarzNewDto);

        Lekarz newLekarz = lekarzRepository.save(lekarzMapper.map(lekarzNewDto));

        return lekarzMapper.map(newLekarz);
    }

    @Override
    public LekarzDetailViewDto update(LekarzEditDto lekarzEditDto) {
        checkNotNull(lekarzEditDto, "20190815181333");

        lekarzValidator.check(lekarzEditDto);

        Lekarz lekarz = findLekarzByIdOrThrowException(lekarzEditDto.getId());
        lekarzMapper.map(lekarz, lekarzEditDto);
        lekarz = lekarzRepository.save(lekarz);

        return lekarzMapper.map(lekarz);
    }

    private Lekarz findLekarzByIdOrThrowException(Long lekarzId) {
        return lekarzRepository.findById(lekarzId).orElseThrow(() -> new LekarzNotFoundException(lekarzId));
    }

}
