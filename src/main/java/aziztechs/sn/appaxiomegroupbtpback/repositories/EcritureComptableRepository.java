package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.EcritureComptable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EcritureComptableRepository extends JpaRepository<EcritureComptable, Long> {
    List<EcritureComptable> findByFactureId(Long factureId);
    List<EcritureComptable> findByType(String type);
    List<EcritureComptable> findByDateBetween(java.time.LocalDate start, java.time.LocalDate end);

    @Query("SELECT SUM(e.montant) FROM EcritureComptable e WHERE e.type = :type")
    Double getTotalByType(String type);
}
