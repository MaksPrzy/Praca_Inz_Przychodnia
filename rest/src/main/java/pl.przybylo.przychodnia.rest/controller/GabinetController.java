package pl.przybylo.przychodnia.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.przybylo.przychodnia.business.GabinetService;
import pl.przybylo.przychodnia.dto.gabinet.GabinetEditDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetNewDto;
import pl.przybylo.przychodnia.dto.gabinet.GabinetViewDto;

import java.util.List;

@CrossOrigin(origins = "*") // todo: do usuniecia
@RestController
@RequiredArgsConstructor
@RequestMapping("/gabinety")
public class GabinetController {

    private final GabinetService gabinetService;

    @GetMapping
    public List<GabinetViewDto> getGabinetList() {
        return gabinetService.getGabinetList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GabinetViewDto add(@RequestBody GabinetNewDto gabinetNewDto) {
        return gabinetService.add(gabinetNewDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GabinetViewDto update(@PathVariable("id") Long id, @RequestBody GabinetEditDto gabinetEditDto) {
        return gabinetService.update(gabinetEditDto);
    }

}
