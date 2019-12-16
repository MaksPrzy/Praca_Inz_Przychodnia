package pl.przybylo.przychodnia.business.impl.uzytkownik;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.UzytkownikService;
import pl.przybylo.przychodnia.commons.exceptions.BadLoginOrPasswordException;
import pl.przybylo.przychodnia.domain.model.Pacjent;
import pl.przybylo.przychodnia.domain.repository.PacjentRepository;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;
import pl.przybylo.przychodnia.dto.uzytkownik.UzytkownikDto;
import pl.przybylo.przychodnia.mapper.PacjentMapper;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class UzytkownikServiceImpl implements UzytkownikService, UserDetailsService {

    private final PacjentRepository pacjentRepository;
    private final PacjentMapper pacjentMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        checkArgument(isNotBlank(email), "20191211201700");

        Pacjent uzytkownik = pacjentRepository.findByEmail(email).orElseThrow(() -> new BadLoginOrPasswordException());
        PacjentDetailViewDto pacjentDetailViewDto = pacjentMapper.map(uzytkownik);

        return new UzytkownikDto(pacjentDetailViewDto);
    }

}
