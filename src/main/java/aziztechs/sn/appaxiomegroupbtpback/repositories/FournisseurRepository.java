package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    List<Fournisseur> findByRaisonSocialeContainingIgnoreCase(String raisonSociale);
    List<Fournisseur> findByNotationGreaterThanEqual(int notation);
    List<Fournisseur> findByAdresseContainingIgnoreCase(String adresse);
}
