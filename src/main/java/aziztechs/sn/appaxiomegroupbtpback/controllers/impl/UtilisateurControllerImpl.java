package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;


import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.UtilisateurController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.UtilisateurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.UtilisateurResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurControllerImpl implements UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurControllerImpl(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurResponseDTO> createUtilisateur(@RequestBody UtilisateurRequestDTO utilisateurDTO) {
        UtilisateurResponseDTO response = utilisateurService.createUtilisateur(utilisateurDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UtilisateurResponseDTO> updateUtilisateur(@PathVariable Long id,
                                                                    @RequestBody UtilisateurRequestDTO utilisateurDTO) {
        UtilisateurResponseDTO response = utilisateurService.updateUtilisateur(id, utilisateurDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UtilisateurResponseDTO> getUtilisateurById(@PathVariable Long id) {
        UtilisateurResponseDTO response = utilisateurService.getUtilisateurById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UtilisateurResponseDTO>> getAllUtilisateurs() {
        List<UtilisateurResponseDTO> response = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UtilisateurResponseDTO> assignRolesToUtilisateur(@PathVariable Long utilisateurId,
                                                                           @RequestBody List<Long> roleIds) {
        UtilisateurResponseDTO response = utilisateurService.assignRolesToUtilisateur(utilisateurId, roleIds);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UtilisateurResponseDTO> getCurrentUserProfile() {
        UtilisateurResponseDTO response = utilisateurService.getCurrentUserProfile();
        return ResponseEntity.ok(response);
    }
}
