package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.WizytaService;
import pl.przybylo.przychodnia.dto.wizyta.HarmonogramZaplanowanaWizytaDto;
import pl.przybylo.przychodnia.dto.wizyta.HarmonogramZaplanowanaWizytaRequestDto;

import java.util.List;

@CrossOrigin(origins = "*") // todo: do usuniecia
@RestController
@RequiredArgsConstructor
@RequestMapping("/planowanie")
public class PlanowanieWizytyController {

    private final WizytaService wizytaService;

    @PostMapping
    public List<HarmonogramZaplanowanaWizytaDto> getZaplanowanaWizytaList(@RequestBody HarmonogramZaplanowanaWizytaRequestDto harmonogramZaplanowanaWizytaRequestDto) {
        return wizytaService.getZaplanowanaWizytaNaTydzienList(
                harmonogramZaplanowanaWizytaRequestDto.getDateFrom(),
                harmonogramZaplanowanaWizytaRequestDto.getDateTo(),
                harmonogramZaplanowanaWizytaRequestDto.getLekarzId(),
                harmonogramZaplanowanaWizytaRequestDto.getSpecjalizacjaId()
        );
    }

}
