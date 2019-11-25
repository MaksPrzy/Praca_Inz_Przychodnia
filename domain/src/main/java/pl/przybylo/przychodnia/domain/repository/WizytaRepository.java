package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.przybylo.przychodnia.commons.exceptions.WizytaNotFoundException;
import pl.przybylo.przychodnia.domain.model.wizyta.Wizyta;

import java.util.List;

public interface WizytaRepository extends JpaRepository<Wizyta, Long>, Repository<Wizyta> {

    List<Wizyta> findByPacjentId(Long pacjentId);

    default Wizyta findByIdOrThrowException(long id) {
        return findById(id).orElseThrow(() -> new WizytaNotFoundException(id));
    }

}
