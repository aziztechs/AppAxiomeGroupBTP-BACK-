package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.EcritureComptableRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.EcritureComptableResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Écritures Comptables", description = "API pour la gestion des écritures comptables")
@RequestMapping("/api/ecritures-comptables")
public interface EcritureComptableController {

    @Operation(summary = "Créer une nouvelle écriture comptable")
    @PostMapping
    ResponseEntity<EcritureComptableResponseDTO> createEcritureComptable(@Valid @RequestBody EcritureComptableRequestDTO ecritureDTO);

    @Operation(summary = "Mettre à jour une écriture comptable")
    @PutMapping("/{id}")
    ResponseEntity<EcritureComptableResponseDTO> updateEcritureComptable(@PathVariable Long id, @Valid @RequestBody EcritureComptableRequestDTO ecritureDTO);

    @Operation(summary = "Supprimer une écriture comptable")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteEcritureComptable(@PathVariable Long id);

    @Operation(summary = "Récupérer une écriture comptable par son ID")
    @GetMapping("/{id}")
    ResponseEntity<EcritureComptableResponseDTO> getEcritureComptableById(@PathVariable Long id);

    @Operation(summary = "Récupérer les écritures comptables par facture")
    @GetMapping("/facture/{factureId}")
    ResponseEntity<List<EcritureComptableResponseDTO>> getEcrituresByFacture(@PathVariable Long factureId);

    @Operation(summary = "Récupérer les écritures comptables par type")
    @GetMapping("/type/{type}")
    ResponseEntity<List<EcritureComptableResponseDTO>> getEcrituresByType(@PathVariable String type);

    @Operation(summary = "Récupérer le total des montants par type")
    @GetMapping("/total/type/{type}")
    ResponseEntity<Double> getTotalByType(@PathVariable String type);
}

