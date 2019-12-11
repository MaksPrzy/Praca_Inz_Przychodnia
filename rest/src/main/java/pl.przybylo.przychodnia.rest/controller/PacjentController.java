package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.PacjentService;
import pl.przybylo.przychodnia.business.WizytaService;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentEditDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentNewDto;
import pl.przybylo.przychodnia.dto.wizyta.WizytaViewDto;
import pl.przybylo.przychodnia.dto.wizyta.ZakonczWizyteDto;
import pl.przybylo.przychodnia.dto.wizyta.ZaplanujWizyteDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacjenci")
public class PacjentController {

    private final PacjentService pacjentService;
    private final WizytaService wizytaService;

    @GetMapping
    public List<PacjentDetailViewDto> getPacjentList() {
        return pacjentService.getPacjentList();
    }

    @GetMapping("/{id}")
    public PacjentDetailViewDto getPacjent(@PathVariable Long id) {
        return pacjentService.getPacjent(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacjentDetailViewDto add(@RequestBody PacjentNewDto pacjentNewDto) {
        return pacjentService.add(pacjentNewDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacjentDetailViewDto update(@PathVariable Long id, @RequestBody PacjentEditDto pacjentEditDto) {
        return pacjentService.update(pacjentEditDto);
    }

    //OBSŁUGA WIZYT

    @GetMapping("/{id}/wizyty")
    public List<WizytaViewDto> getWizytaList(@RequestParam long pacjentId) {
        return wizytaService.getWizytaList(pacjentId);
    }

//    @GetMapping("/{id}/wizyty")
//    public WizytaViewDto getWizyta(@PathVariable long id) {          wywala błąd !!!!!!!
//        return wizytaService.getWizyta(id);
//    }

    @PostMapping("/{id}/wizyty")
    @ResponseStatus(HttpStatus.CREATED)
    public void ZaplanowanaWizyta(@PathVariable("id") long id, @RequestBody ZaplanujWizyteDto zaplanujWizyteDto) {
        wizytaService.zaplanuj(zaplanujWizyteDto);
    }

    @PutMapping("/{id}/wizyty/{wizytyId}")
    @ResponseStatus(HttpStatus.OK)
    public void ZakonczonaWizyta(@PathVariable("id") Long id,
                                 @PathVariable("wizytyId") Long wizytyId,
                                 @RequestBody ZakonczWizyteDto zakonczWizyteDto) {
        wizytaService.zakoncz(zakonczWizyteDto);
    }

    @DeleteMapping("/{id}/wizyty/{wizytyId}")
    @ResponseStatus(HttpStatus.OK)
    public void UsunWizyte(@PathVariable ("id") long id,
                           @PathVariable ("wizytyId") Long wizytyId,
                           @RequestBody WizytaViewDto WizytaViewDto) {
        wizytaService.delete(id);
    }



}
