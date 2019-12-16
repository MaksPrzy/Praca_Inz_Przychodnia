package pl.przybylo.przychodnia.dto.uzytkownik;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;

import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class UzytkownikDto implements UserDetails {

    @Getter
    private PacjentDetailViewDto uzytkownik;

    public UzytkownikDto(PacjentDetailViewDto uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return newArrayList();
    }

    @Override
    public String getPassword() {
        return uzytkownik.getHaslo();
    }

    @Override
    public String getUsername() {
        return uzytkownik.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
