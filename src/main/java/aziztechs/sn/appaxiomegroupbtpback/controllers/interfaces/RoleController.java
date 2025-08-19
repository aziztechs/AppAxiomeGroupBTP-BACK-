package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.RoleRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.RoleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Rôles", description = "API pour la gestion des rôles et permissions")
@RequestMapping("/api/roles")
public interface RoleController {

    @Operation(summary = "Créer un nouveau rôle")
    @PostMapping
    ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody RoleRequestDTO roleDTO);

    @Operation(summary = "Mettre à jour un rôle")
    @PutMapping("/{id}")
    ResponseEntity<RoleResponseDTO> updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequestDTO roleDTO);

    @Operation(summary = "Supprimer un rôle")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRole(@PathVariable Long id);

    @Operation(summary = "Récupérer un rôle par son ID")
    @GetMapping("/{id}")
    ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable Long id);

    @Operation(summary = "Récupérer tous les rôles")
    @GetMapping
    ResponseEntity<List<RoleResponseDTO>> getAllRoles();

    @Operation(summary = "Ajouter des permissions à un rôle")
    @PostMapping("/{id}/permissions")
    ResponseEntity<RoleResponseDTO> addPermissionsToRole(
            @PathVariable("id") Long roleId,
            @RequestBody List<String> permissions);

    @Operation(summary = "Retirer des permissions d'un rôle")
    @DeleteMapping("/{id}/permissions")
    ResponseEntity<RoleResponseDTO> removePermissionsFromRole(
            @PathVariable("id") Long roleId,
            @RequestBody List<String> permissions);
}

