package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.przybylo.przychodnia.commons.exceptions.PacjentNotFoundException;
import pl.przybylo.przychodnia.domain.model.Pacjent;

public interface PacjentRepository extends JpaRepository<Pacjent, Long>, Repository<Pacjent> {

    default Pacjent findByIdOrThrowException(long id) {
        return findById(id).orElseThrow(() -> new PacjentNotFoundException(id));
    }

}
