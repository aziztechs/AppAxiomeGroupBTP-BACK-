package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.DaRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.DaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Demandes d'Achat", description = "API pour la gestion des demandes d'achat (DA)")
@RequestMapping("/api/das")
public interface DaController {

    @Operation(summary = "Créer une nouvelle demande d'achat")
    @PostMapping
    ResponseEntity<DaResponseDTO> createDa(@Valid @RequestBody DaRequestDTO daDTO);

    @Operation(summary = "Mettre à jour une demande d'achat")
    @PutMapping("/{id}")
    ResponseEntity<DaResponseDTO> updateDa(@PathVariable Long id, @Valid @RequestBody DaRequestDTO daDTO);

    @Operation(summary = "Supprimer une demande d'achat")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDa(@PathVariable Long id);

    @Operation(summary = "Récupérer une demande d'achat par son ID")
    @GetMapping("/{id}")
    ResponseEntity<DaResponseDTO> getDaById(@PathVariable Long id);

    @Operation(summary = "Récupérer toutes les demandes d'achat")
    @GetMapping
    ResponseEntity<List<DaResponseDTO>> getAllDas();

    @Operation(summary = "Récupérer les demandes d'achat par statut")
    @GetMapping("/statut/{statut}")
    ResponseEntity<List<DaResponseDTO>> getDasByStatut(@PathVariable String statut);

    @Operation(summary = "Mettre à jour le statut d'une demande d'achat")
    @PatchMapping("/{id}/statut")
    ResponseEntity<DaResponseDTO> updateDaStatut(@PathVariable Long id, @RequestParam String statut);
}

