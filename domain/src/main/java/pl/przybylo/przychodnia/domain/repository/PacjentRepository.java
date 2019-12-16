package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.przybylo.przychodnia.commons.exceptions.PacjentNotFoundException;
import pl.przybylo.przychodnia.domain.model.Pacjent;

import java.util.Optional;

public interface PacjentRepository extends JpaRepository<Pacjent, Long>, Repository<Pacjent> {

    Optional<Pacjent> findByEmail(String email);

    default Pacjent findByIdOrThrowException(long id) {
        return findById(id).orElseThrow(() -> new PacjentNotFoundException(id));
    }

}
