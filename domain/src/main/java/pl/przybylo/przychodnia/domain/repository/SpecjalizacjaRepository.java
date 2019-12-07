package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.przybylo.przychodnia.domain.model.Specjalizacja;

public interface SpecjalizacjaRepository extends JpaRepository<Specjalizacja, Long> {

}

