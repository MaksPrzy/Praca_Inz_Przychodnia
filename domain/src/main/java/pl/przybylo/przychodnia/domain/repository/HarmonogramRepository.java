package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.przybylo.przychodnia.domain.model.Harmonogram;

public interface HarmonogramRepository extends CrudRepository<Harmonogram, Long> {
}
