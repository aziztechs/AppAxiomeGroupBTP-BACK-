package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Jalon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JalonRepository extends JpaRepository<Jalon, Long> {
    List<Jalon> findByChantierId(Long chantierId);
    List<Jalon> findByStatut(String statut);
    List<Jalon> findByDateFinBeforeAndStatutNot(java.time.LocalDate date, String statut);
}
