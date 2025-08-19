package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.PopRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.PopResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "POPs", description = "API pour la gestion des Procès-Verbaux d'Ouverture des Plis (POP)")
@RequestMapping("/api/pops")
public interface PopController {

    @Operation(summary = "Créer un nouveau POP")
    @PostMapping
    ResponseEntity<PopResponseDTO> createPop(@Valid @RequestBody PopRequestDTO popDTO);

    @Operation(summary = "Mettre à jour un POP")
    @PutMapping("/{id}")
    ResponseEntity<PopResponseDTO> updatePop(@PathVariable Long id, @Valid @RequestBody PopRequestDTO popDTO);

    @Operation(summary = "Supprimer un POP")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePop(@PathVariable Long id);

    @Operation(summary = "Récupérer un POP par son ID")
    @GetMapping("/{id}")
    ResponseEntity<PopResponseDTO> getPopById(@PathVariable Long id);

    @Operation(summary = "Récupérer tous les POPs")
    @GetMapping
    ResponseEntity<List<PopResponseDTO>> getAllPops();

    @Operation(summary = "Récupérer les POPs par statut")
    @GetMapping("/statut/{statut}")
    ResponseEntity<List<PopResponseDTO>> getPopsByStatut(@PathVariable String statut);

    @Operation(summary = "Mettre à jour le statut d'un POP")
    @PatchMapping("/{id}/statut")
    ResponseEntity<PopResponseDTO> updatePopStatut(@PathVariable Long id, @RequestParam String statut);
}

