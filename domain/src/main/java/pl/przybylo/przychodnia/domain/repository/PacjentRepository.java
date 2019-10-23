package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.przybylo.przychodnia.domain.model.Pacjent;

public interface PacjentRepository extends JpaRepository<Pacjent, Long> {

}
