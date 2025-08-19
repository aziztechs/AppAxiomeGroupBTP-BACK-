package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.UtilisateurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.UtilisateurResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.Chantier;

import java.util.List;

public interface UtilisateurService {
    UtilisateurResponseDTO createUtilisateur(UtilisateurRequestDTO utilisateurDTO);
    UtilisateurResponseDTO updateUtilisateur(Long id, UtilisateurRequestDTO utilisateurDTO);
    void deleteUtilisateur(Long id);
    UtilisateurResponseDTO getUtilisateurById(Long id);
    List<UtilisateurResponseDTO> getAllUtilisateurs();
    UtilisateurResponseDTO assignRolesToUtilisateur(Long utilisateurId, List<Long> roleIds);

    UtilisateurResponseDTO getCurrentUserProfile();

    Chantier getChantierByReference(String reference);
}
