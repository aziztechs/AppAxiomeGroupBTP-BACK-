package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FournisseurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FournisseurResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Fournisseurs", description = "API pour la gestion des fournisseurs")
@RequestMapping("/api/fournisseurs")
public interface FournisseurController {

    @Operation(summary = "Créer un nouveau fournisseur")
    @PostMapping
    ResponseEntity<FournisseurResponseDTO> createFournisseur(@Valid @RequestBody FournisseurRequestDTO fournisseurDTO);

    @Operation(summary = "Mettre à jour un fournisseur")
    @PutMapping("/{id}")
    ResponseEntity<FournisseurResponseDTO> updateFournisseur(@PathVariable Long id, @Valid @RequestBody FournisseurRequestDTO fournisseurDTO);

    @Operation(summary = "Supprimer un fournisseur")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFournisseur(@PathVariable Long id);

    @Operation(summary = "Récupérer un fournisseur par son ID")
    @GetMapping("/{id}")
    ResponseEntity<FournisseurResponseDTO> getFournisseurById(@PathVariable Long id);

    @Operation(summary = "Récupérer tous les fournisseurs")
    @GetMapping
    ResponseEntity<List<FournisseurResponseDTO>> getAllFournisseurs();

    @Operation(summary = "Rechercher des fournisseurs par mot-clé")
    @GetMapping("/search")
    ResponseEntity<List<FournisseurResponseDTO>> searchFournisseurs(@RequestParam(required = false) String keyword);

    @Operation(summary = "Mettre à jour la notation d'un fournisseur")
    @PatchMapping("/{id}/notation")
    ResponseEntity<FournisseurResponseDTO> updateFournisseurNotation(@PathVariable Long id, @RequestParam Integer notation);
}

