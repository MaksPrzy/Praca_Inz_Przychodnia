package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.przybylo.przychodnia.domain.model.Lekarz;

import java.util.List;

public interface LekarzRepository extends JpaRepository<Lekarz, Long> {

    List<Lekarz> findByFullTextSearchContains(String searchBy);

}
