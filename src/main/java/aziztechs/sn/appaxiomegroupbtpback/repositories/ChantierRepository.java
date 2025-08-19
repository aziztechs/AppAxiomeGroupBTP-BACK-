package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Chantier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChantierRepository extends JpaRepository<Chantier, Integer> {
    Optional<Chantier> findByReference(String reference);
    List<Chantier> findByClientContainingIgnoreCase(String client);
    List<Chantier> findByLocalisationContainingIgnoreCase(String localisation);

    @Query("SELECT c FROM Chantier c WHERE c.dateFin >= CURRENT_DATE")
    List<Chantier> findActiveChantiers();

    @Query("SELECT c FROM Chantier c WHERE c.dateFin < CURRENT_DATE")
    List<Chantier> findCompletedChantiers();

    int countByStatut(String enCours);
}
