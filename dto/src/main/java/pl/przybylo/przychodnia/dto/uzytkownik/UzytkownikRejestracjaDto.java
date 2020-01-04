package pl.przybylo.przychodnia.dto.uzytkownik;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.przybylo.przychodnia.dto.pacjent.AdresDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;

import java.time.LocalDate;
import java.util.Collection;

import static com.google.common.collect.Lists.newArrayList;

public class UzytkownikRejestracjaDto implements UserDetails {

    @Getter
    private PacjentDetailViewDto uzytkownikRejestracja;

    public UzytkownikRejestracjaDto(PacjentDetailViewDto uzytkownikRejestracja) {
        this.uzytkownikRejestracja = uzytkownikRejestracja;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return newArrayList();
    }

    public String getPesel() {
        return uzytkownikRejestracja.getPesel();
    }

    public String getImie() {
        return uzytkownikRejestracja.getImie();
    }

    public String getNazwisko() {
        return uzytkownikRejestracja.getNazwisko();
    }

    @Override
    public String getUsername() {
        return uzytkownikRejestracja.getEmail();
    }

    @Override
    public String getPassword() {
        return uzytkownikRejestracja.getHaslo();
    }

    public String getTelefonKomorkowy() {
        return uzytkownikRejestracja.getTelefonKomorkowy();
    }

    public LocalDate getDataUrodzenia() {
        return uzytkownikRejestracja.getDataUrodzenia();
    }

    public AdresDto getKodPocztowy() {
        return uzytkownikRejestracja.getAdres();
    }

    public AdresDto getMiejscowosc() {
        return uzytkownikRejestracja.getAdres();
    }

    public AdresDto getUlica() {
        return uzytkownikRejestracja.getAdres();
    }

    public AdresDto getNumerDomu() {
        return uzytkownikRejestracja.getAdres();
    }
    
    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
