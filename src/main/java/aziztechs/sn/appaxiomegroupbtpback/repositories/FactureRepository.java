package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    Optional<Facture> findByNumero(String numero);
    List<Facture> findByStatut(String statut);
    List<Facture> findByDateBetween(java.time.LocalDate start, java.time.LocalDate end);

    @Query("SELECT SUM(f.montant) FROM Facture f WHERE f.statut = 'PAYEE'")
    Double getTotalFacturesPayees();
}
