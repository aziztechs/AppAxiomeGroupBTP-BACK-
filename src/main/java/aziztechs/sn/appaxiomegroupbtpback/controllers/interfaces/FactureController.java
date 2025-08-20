package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FactureRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FactureResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Factures", description = "API pour la gestion des factures")
@RequestMapping("/api/factures")
public interface FactureController {

    @Operation(summary = "Créer une nouvelle facture")
    @PostMapping
    ResponseEntity<FactureResponseDTO> createFacture(@Valid @RequestBody FactureRequestDTO factureDTO);

    @Operation(summary = "Mettre à jour une facture")
    @PutMapping("/{id}")
    ResponseEntity<FactureResponseDTO> updateFacture(@PathVariable Long id, @Valid @RequestBody FactureRequestDTO factureDTO);

    @Operation(summary = "Supprimer une facture")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFacture(@PathVariable Long id);

    @Operation(summary = "Récupérer une facture par son ID")
    @GetMapping("/{id}")
    ResponseEntity<FactureResponseDTO> getFactureById(@PathVariable Long id);

    @Operation(summary = "Récupérer toutes les factures")
    @GetMapping
    ResponseEntity<List<FactureResponseDTO>> getAllFactures();

    @Operation(summary = "Récupérer les factures par statut")
    @GetMapping("/statut/{statut}")
    ResponseEntity<List<FactureResponseDTO>> getFacturesByStatut(@PathVariable String statut);

    @Operation(summary = "Mettre à jour le statut d'une facture")
    @PatchMapping("/{id}/statut")
    ResponseEntity<FactureResponseDTO> updateFactureStatut(@PathVariable Long id, @RequestParam String statut);

    @Operation(summary = "Récupérer le total des factures payées")
    @GetMapping("/total/payees")
    ResponseEntity<Double> getTotalFacturesPayees();
}

