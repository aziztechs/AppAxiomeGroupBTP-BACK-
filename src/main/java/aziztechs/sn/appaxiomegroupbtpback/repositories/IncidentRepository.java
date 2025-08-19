package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByChantierId(Long chantierId);
    List<Incident> findByTypeAndStatut(String type, String statut);
    List<Incident> findByDateBetween(java.time.LocalDate start, java.time.LocalDate end);
}
