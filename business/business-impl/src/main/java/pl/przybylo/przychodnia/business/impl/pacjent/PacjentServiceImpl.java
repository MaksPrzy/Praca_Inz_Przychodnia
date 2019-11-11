package pl.przybylo.przychodnia.business.impl.pacjent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.PacjentService;
import pl.przybylo.przychodnia.commons.exceptions.PacjentNotFoundException;
import pl.przybylo.przychodnia.domain.model.Pacjent;
import pl.przybylo.przychodnia.domain.repository.PacjentRepository;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentEditDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentNewDto;
import pl.przybylo.przychodnia.mapper.PacjentMapper;

import java.util.List;
import java.util.stream.Collectors;

import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Service
@RequiredArgsConstructor
public class PacjentServiceImpl implements PacjentService {

    private final NumerKartotekiGeneratorService numerKartotekiGeneratorService;
    private final PacjentRepository pacjentRepository;
    private final PacjentMapper pacjentMapper;
    private final PacjentValidator pacjentValidator;

    @Override
    public List<PacjentDetailViewDto> getPacjentList() {
        return pacjentRepository.findAll().stream()
                .map(pacjentMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public PacjentDetailViewDto getPacjent(Long id) {
        checkNotNull(id, "20191015182921");

        Pacjent pacjent = pacjentRepository.findById(id).orElseThrow(() -> new PacjentNotFoundException(id));
        return pacjentMapper.map(pacjent);
    }

    @Override
    public PacjentDetailViewDto add(PacjentNewDto pacjentNewDto) {
        checkNotNull(pacjentNewDto, "20190606195002");

        pacjentValidator.check(pacjentNewDto);

        String numerKartoteki = numerKartotekiGeneratorService.generate(pacjentNewDto);
        Pacjent pacjent = pacjentMapper.map(numerKartoteki, pacjentNewDto);
        Pacjent newPacjent = pacjentRepository.save(pacjent);

        return pacjentMapper.map(newPacjent);
    }

    @Override
    public PacjentDetailViewDto update(PacjentEditDto pacjentEditDto) {
        checkNotNull(pacjentEditDto, "20191013230028");
        checkNotNull(pacjentEditDto.getId(), "20191013230028");

        pacjentValidator.check(pacjentEditDto);

        Long pacjentId = pacjentEditDto.getId();
        Pacjent pacjent = pacjentRepository.findById(pacjentId).orElseThrow(() -> new PacjentNotFoundException(pacjentId));
        pacjentMapper.mapToEdit(pacjentEditDto);
        pacjent = pacjentRepository.save(pacjent);

        return pacjentMapper.map(pacjent);
    }

}
