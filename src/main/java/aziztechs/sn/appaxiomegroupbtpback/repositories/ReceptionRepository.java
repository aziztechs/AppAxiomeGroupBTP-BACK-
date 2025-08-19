package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Reception;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionRepository extends JpaRepository<Reception, Long> {
    List<Reception> findByConformite(boolean conformite);
    List<Reception> findByDateBetween(java.time.LocalDate start, java.time.LocalDate end);
}
