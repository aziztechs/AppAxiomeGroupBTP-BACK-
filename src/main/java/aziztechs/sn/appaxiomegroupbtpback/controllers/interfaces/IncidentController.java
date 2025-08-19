package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.IncidentRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.IncidentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Incidents", description = "API pour la gestion des incidents sur les chantiers")
@RequestMapping("/api/incidents")
public interface IncidentController {

    @Operation(summary = "Créer un nouvel incident")
    @PostMapping
    ResponseEntity<IncidentResponseDTO> createIncident(@Valid @RequestBody IncidentRequestDTO incidentDTO);

    @Operation(summary = "Mettre à jour un incident")
    @PutMapping("/{id}")
    ResponseEntity<IncidentResponseDTO> updateIncident(@PathVariable Long id, @Valid @RequestBody IncidentRequestDTO incidentDTO);

    @Operation(summary = "Supprimer un incident")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteIncident(@PathVariable Long id);

    @Operation(summary = "Récupérer un incident par son ID")
    @GetMapping("/{id}")
    ResponseEntity<IncidentResponseDTO> getIncidentById(@PathVariable Long id);

    @Operation(summary = "Récupérer les incidents par chantier")
    @GetMapping("/chantier/{chantierId}")
    ResponseEntity<List<IncidentResponseDTO>> getIncidentsByChantier(@PathVariable Long chantierId);

    @Operation(summary = "Récupérer les incidents par type et statut")
    @GetMapping("/search")
    ResponseEntity<List<IncidentResponseDTO>> getIncidentsByTypeAndStatut(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String statut);

    @Operation(summary = "Mettre à jour le statut d'un incident")
    @PatchMapping("/{id}/statut")
    ResponseEntity<IncidentResponseDTO> updateIncidentStatut(@PathVariable Long id, @RequestParam String statut);
}

