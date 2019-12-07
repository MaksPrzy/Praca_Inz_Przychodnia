package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.HarmonogramService;
import pl.przybylo.przychodnia.business.LekarzService;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramEditDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramNewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramPozycjaViewDto;
import pl.przybylo.przychodnia.dto.harmonogram.HarmonogramViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzEditDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzNewDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lekarze")
public class LekarzController {

    private final LekarzService lekarzService;
    private final HarmonogramService harmonogramService;

    @GetMapping
    public List<LekarzDetailViewDto> getLekarzList(@RequestParam String searchBy) {
        return lekarzService.getLekarzList(searchBy);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LekarzDetailViewDto add(@RequestBody LekarzNewDto lekarzNewDto) {
        return lekarzService.add(lekarzNewDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LekarzDetailViewDto update(@PathVariable("id") Long id, @RequestBody LekarzEditDto lekarzEditDto) {
        return lekarzService.update(lekarzEditDto);
    }

    // obsluga harmonogramow
    // np. /api/lekarze/9/harmonogramy
    @GetMapping("/{id}/harmonogramy")
    public List<HarmonogramViewDto> getHarmonogramList(@PathVariable("id") long id) {
        return harmonogramService.getHarmonogramList(id);
    }

    @PostMapping("/{id}/harmonogramy")
    @ResponseStatus(HttpStatus.CREATED)
    public HarmonogramViewDto add(@PathVariable("id") long id, @RequestBody HarmonogramNewDto harmonogramNewDto) {
        return harmonogramService.add(harmonogramNewDto);
    }

    @PutMapping("/{id}/harmonogramy/{harmonogramId}")
    @ResponseStatus(HttpStatus.OK)
    public HarmonogramViewDto update(@PathVariable("id") Long id,
                                     @PathVariable("harmonogramId") Long harmonogramId,
                                     @RequestBody HarmonogramEditDto harmonogramEditDto) {
        return harmonogramService.update(harmonogramEditDto);
    }

    @DeleteMapping("/{id}/harmonogramy/{harmonogramId}")
    public void delete(@PathVariable("id") long id,
                       @PathVariable("harmonogramId") Long harmonogramId,
                       @RequestBody HarmonogramPozycjaViewDto harmonogramPozycjaViewDto) {
        harmonogramService.delete(id);
    }

}
