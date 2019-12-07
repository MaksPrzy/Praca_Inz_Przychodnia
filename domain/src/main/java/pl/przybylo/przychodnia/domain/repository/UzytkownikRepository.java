package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.repository.CrudRepository;
import pl.przybylo.przychodnia.domain.model.Uzytkownik;

public interface UzytkownikRepository extends CrudRepository<Uzytkownik, Long> {
}
