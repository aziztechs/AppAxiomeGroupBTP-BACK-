package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.POP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PopRepository extends JpaRepository<POP, Long> {
    Optional<POP> findByReference(String reference);
    List<POP> findByStatut(String statut);
    List<POP> findByDateBetween(java.time.LocalDate start, java.time.LocalDate end);
    List<POP> findByMontantGreaterThan(double montant);
}
