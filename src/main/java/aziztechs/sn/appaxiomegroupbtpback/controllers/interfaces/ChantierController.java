package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;


import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.ChantierRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ChantierDetailResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ChantierResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Chantiers", description = "API pour la gestion des chantiers")
@RequestMapping("/api/chantiers")
public interface ChantierController {

    @Operation(summary = "Créer un nouveau chantier")
    @PostMapping
    ResponseEntity<ChantierResponseDTO> createChantier(@RequestBody ChantierRequestDTO chantierDTO);

    @Operation(summary = "Mettre à jour un chantier")
    @PutMapping("/{id}")
    ResponseEntity<ChantierResponseDTO> updateChantier(
            @PathVariable Long id,
            @RequestBody ChantierRequestDTO chantierDTO);

    @Operation(summary = "Annuler un chantier")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteChantier(@PathVariable Long id);

    @Operation(summary = "Récupérer un chantier par son ID")
    @GetMapping("/{id}")
    ResponseEntity<ChantierResponseDTO> getChantierById(@PathVariable Long id);

    @Operation(summary = "Récupérer les détails complets d'un chantier")
    @GetMapping("/{id}/details")
    ResponseEntity<ChantierDetailResponseDTO> getChantierDetails(@PathVariable Long id);

    @Operation(summary = "Lister tous les chantiers")
    @GetMapping
    ResponseEntity<List<ChantierResponseDTO>> getAllChantiers();

    @Operation(summary = "Lister les chantiers par statut")
    @GetMapping("/statut/{statut}")
    ResponseEntity<List<ChantierResponseDTO>> getChantiersByStatut(@PathVariable String statut);

    @Operation(summary = "Lister les chantiers en retard")
    @GetMapping("/retard")
    ResponseEntity<List<ChantierResponseDTO>> getChantiersEnRetard();
}
