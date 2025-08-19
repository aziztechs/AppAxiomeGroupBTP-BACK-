package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.DA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DaRepository extends JpaRepository<DA, Long> {
    Optional<DA> findByReference(String reference);
    List<DA> findByStatut(String statut);
    List<DA> findByDateEmissionBetween(java.time.LocalDate start, java.time.LocalDate end);
    List<DA> findByMontantGreaterThan(double montant);
}
