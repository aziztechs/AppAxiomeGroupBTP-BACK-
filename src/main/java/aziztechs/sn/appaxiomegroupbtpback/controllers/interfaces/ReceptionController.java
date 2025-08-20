package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.ReceptionRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ReceptionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Réceptions", description = "API pour la gestion des réceptions de matériaux")
@RequestMapping("/api/receptions")
public interface ReceptionController {

    @Operation(summary = "Créer une nouvelle réception")
    @PostMapping
    ResponseEntity<ReceptionResponseDTO> createReception(@Valid @RequestBody ReceptionRequestDTO receptionDTO);

    @Operation(summary = "Mettre à jour une réception")
    @PutMapping("/{id}")
    ResponseEntity<ReceptionResponseDTO> updateReception(@PathVariable Long id, @Valid @RequestBody ReceptionRequestDTO receptionDTO);

    @Operation(summary = "Récupérer une réception par son ID")
    @GetMapping("/{id}")
    ResponseEntity<ReceptionResponseDTO> getReceptionById(@PathVariable Long id);

    @Operation(summary = "Récupérer toutes les réceptions")
    @GetMapping
    ResponseEntity<List<ReceptionResponseDTO>> getAllReceptions();

    @Operation(summary = "Récupérer les réceptions par conformité")
    @GetMapping("/conformite")
    ResponseEntity<List<ReceptionResponseDTO>> getReceptionsByConformite(@RequestParam Boolean conformite);

    @Operation(summary = "Mettre à jour la conformité d'une réception")
    @PatchMapping("/{id}/conformite")
    ResponseEntity<ReceptionResponseDTO> updateReceptionConformite(@PathVariable Long id, @RequestParam Boolean conformite);
}

