package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.WizytaService;
import pl.przybylo.przychodnia.dto.wizyta.WizytaEditDto;
import pl.przybylo.przychodnia.dto.wizyta.WizytaViewDto;
import pl.przybylo.przychodnia.dto.wizyta.ZakonczWizyteDto;
import pl.przybylo.przychodnia.dto.wizyta.ZaplanujWizyteDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wizyty")
public class WizytaController {

    private final WizytaService wizytaService;

    @GetMapping
    public List<WizytaViewDto> getWizytaList(@RequestParam("pacjentId") long pacjentId) {
        return wizytaService.getWizytaList(pacjentId);
    }

    @GetMapping
    public WizytaViewDto getWizyta(@RequestParam("wizytaId") long id) {
        return wizytaService.getWizyta(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ZaplanowanaWizyta(@RequestBody ZaplanujWizyteDto zaplanujWizyteDto) {
        wizytaService.zaplanuj(zaplanujWizyteDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void ZakonczonaWizyta(@PathVariable("id") Long id, @RequestBody ZakonczWizyteDto zakonczWizyteDto) {
        wizytaService.zakoncz(zakonczWizyteDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void UsunWizyte(@PathVariable("id") Long id, @RequestBody WizytaViewDto WizytaViewDto) {
        wizytaService.delete(id);
    }
}
