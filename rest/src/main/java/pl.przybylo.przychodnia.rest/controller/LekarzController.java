package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.LekarzService;
import pl.przybylo.przychodnia.dto.lekarz.LekarzDetailViewDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzEditDto;
import pl.przybylo.przychodnia.dto.lekarz.LekarzNewDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lekarze")
public class LekarzController {

    private final LekarzService lekarzService;

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

}
