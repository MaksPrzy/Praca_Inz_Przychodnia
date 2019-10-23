package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.przybylo.przychodnia.domain.model.wizyta.Wizyta;

import java.util.List;

public interface WizytaRepository extends CrudRepository<Wizyta, Long> {

    List<Wizyta> findByPacjentId(Long pacjentId);

}
