package pl.przybylo.przychodnia.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.przybylo.przychodnia.domain.model.Gabinet;

public interface GabinetRepository extends JpaRepository<Gabinet, Long> {

}

