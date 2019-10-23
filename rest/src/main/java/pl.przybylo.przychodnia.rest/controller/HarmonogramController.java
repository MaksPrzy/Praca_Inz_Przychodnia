package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.HarmonogramService;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramEditDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramNewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramPozycjaViewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramViewDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/harmonogramy")
public class HarmonogramController {

    private final HarmonogramService harmonogramService;

    // np. /api/harmonogramy?lekarzId=9
    @GetMapping
    public List<HarmonogramViewDto> getHarmonogramList(@RequestParam("lekarzId") long lekarzId) {
        return harmonogramService.getHarmonogramList(lekarzId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HarmonogramViewDto add(@RequestBody HarmonogramNewDto harmonogramNewDto) {
        return harmonogramService.add(harmonogramNewDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HarmonogramViewDto update(@PathVariable("id") Long id, @RequestBody HarmonogramEditDto harmonogramEditDto) {
        return harmonogramService.update(harmonogramEditDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id, @RequestBody HarmonogramPozycjaViewDto harmonogramPozycjaViewDto) {
        harmonogramService.delete(id);
    }
}
