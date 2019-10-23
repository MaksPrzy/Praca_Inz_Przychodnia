package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.przybylo.przychodnia.domain.model.Lekarz;

public interface LekarzRepository extends JpaRepository<Lekarz, Long> {

}
