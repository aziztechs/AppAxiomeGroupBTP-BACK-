package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FournisseurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FournisseurResponseDTO;

import java.util.List;

public interface FournisseurService {
    FournisseurResponseDTO createFournisseur(FournisseurRequestDTO fournisseurDTO);
    FournisseurResponseDTO updateFournisseur(Long id, FournisseurRequestDTO fournisseurDTO);
    void deleteFournisseur(Long id);
    FournisseurResponseDTO getFournisseurById(Long id);
    List<FournisseurResponseDTO> getAllFournisseurs();
    List<FournisseurResponseDTO> searchFournisseurs(String keyword);
    FournisseurResponseDTO updateFournisseurNotation(Long id, Integer notation);
}
