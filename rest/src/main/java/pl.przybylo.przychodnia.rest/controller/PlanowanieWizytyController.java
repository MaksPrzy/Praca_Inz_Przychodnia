package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.HarmonogramService;
import pl.przybylo.przychodnia.business.LekarzService;
import pl.przybylo.przychodnia.business.WizytaService;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramEditDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramNewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramPozycjaViewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzEditDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzNewDto;
import pl.przybylo.przychodnia.dto.wizyta.HarmonogramZaplanowanaWizytaDto;

import java.util.List;

@CrossOrigin(origins = "*") // todo: do usuniecia
@RestController
@RequiredArgsConstructor
@RequestMapping("/planowanie")
public class PlanowanieWizytyController {

    private final WizytaService wizytaService;

    @GetMapping
    public List<HarmonogramZaplanowanaWizytaDto> getZaplanowanaWizytaList() {
        return wizytaService.getZaplanowanaWizytaNaTydzienList(null, null, 0, 0);
    }

}
