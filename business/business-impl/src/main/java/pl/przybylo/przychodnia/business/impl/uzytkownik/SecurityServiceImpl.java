package pl.przybylo.przychodnia.business.impl.uzytkownik;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.przybylo.przychodnia.business.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean hasProtectedAccess() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
    }

}
