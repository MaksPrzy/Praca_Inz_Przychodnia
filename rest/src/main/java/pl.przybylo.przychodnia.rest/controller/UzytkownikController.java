package pl.przybylo.przychodnia.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.przybylo.przychodnia.commons.exceptions.BadLoginOrPasswordException;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;
import pl.przybylo.przychodnia.dto.uzytkownik.UzytkownikDto;
import pl.przybylo.przychodnia.dto.uzytkownik.ZalogowanoDto;
import pl.przybylo.przychodnia.dto.uzytkownik.ZalogujDto;
import pl.przybylo.przychodnia.rest.security.TokenUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/uzytkownicy")
public class UzytkownikController {

    @Value("${app.token.header")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/zaloguj", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> authenticationRequest(@RequestBody ZalogujDto zalogujDto) {
        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            zalogujDto.getUsername(),
                            zalogujDto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(zalogujDto.getUsername());
            final String token = this.tokenUtils.generateToken(userDetails);

            PacjentDetailViewDto uzytkownik = ((UzytkownikDto) userDetails).getUzytkownik();

            return ResponseEntity.ok(new ZalogowanoDto(uzytkownik, token));
        } catch (AuthenticationException e) {
            throw new BadLoginOrPasswordException();
        }
    }

    @RequestMapping(value = "${app.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        String token = request.getHeader(this.tokenHeader);
        String refreshedToken = this.tokenUtils.refreshToken(token);
        return ResponseEntity.ok(new ZalogowanoDto(null, refreshedToken));
    }

}
