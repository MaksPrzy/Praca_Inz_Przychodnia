package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.PacjentService;
import pl.przybylo.przychodnia.dto.pacjent.PacjentDetailViewDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentEditDto;
import pl.przybylo.przychodnia.dto.pacjent.PacjentNewDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pacjenci")
public class PacjentController {

    private final PacjentService pacjentService;

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

}
