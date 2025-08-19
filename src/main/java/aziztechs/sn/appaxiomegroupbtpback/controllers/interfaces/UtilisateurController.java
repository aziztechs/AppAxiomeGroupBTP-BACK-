package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;


import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.UtilisateurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.UtilisateurResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Utilisateurs", description = "API pour la gestion des utilisateurs")
@RequestMapping("/api/utilisateurs")
public interface UtilisateurController {

    @Operation(summary = "Créer un nouvel utilisateur")
    @PostMapping
    ResponseEntity<UtilisateurResponseDTO> createUtilisateur(@RequestBody UtilisateurRequestDTO utilisateurDTO);

    @Operation(summary = "Mettre à jour un utilisateur")
    @PutMapping("/{id}")
    ResponseEntity<UtilisateurResponseDTO> updateUtilisateur(
            @PathVariable Long id,
            @RequestBody UtilisateurRequestDTO utilisateurDTO);

    @Operation(summary = "Désactiver un utilisateur")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id);

    @Operation(summary = "Récupérer un utilisateur par son ID")
    @GetMapping("/{id}")
    ResponseEntity<UtilisateurResponseDTO> getUtilisateurById(@PathVariable Long id);

    @Operation(summary = "Lister tous les utilisateurs")
    @GetMapping
    ResponseEntity<List<UtilisateurResponseDTO>> getAllUtilisateurs();

    @Operation(summary = "Assigner des rôles à un utilisateur")
    @PostMapping("/{utilisateurId}/roles")
    ResponseEntity<UtilisateurResponseDTO> assignRolesToUtilisateur(
            @PathVariable Long utilisateurId,
            @RequestBody List<Long> roleIds);

    @Operation(summary = "Récupérer le profil de l'utilisateur connecté")
    @GetMapping("/me")
    ResponseEntity<UtilisateurResponseDTO> getCurrentUserProfile();
}
