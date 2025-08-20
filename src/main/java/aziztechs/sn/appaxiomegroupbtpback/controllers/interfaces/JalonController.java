package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.JalonRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.JalonResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Jalons", description = "API pour la gestion des jalons des chantiers")
@RequestMapping("/api/jalons")
public interface JalonController {

    @Operation(summary = "Créer un nouveau jalon")
    @PostMapping
    ResponseEntity<JalonResponseDTO> createJalon(@Valid @RequestBody JalonRequestDTO jalonDTO);

    @Operation(summary = "Mettre à jour un jalon")
    @PutMapping("/{id}")
    ResponseEntity<JalonResponseDTO> updateJalon(@PathVariable Long id, @Valid @RequestBody JalonRequestDTO jalonDTO);

    @Operation(summary = "Supprimer un jalon")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJalon(@PathVariable Long id);

    @Operation(summary = "Récupérer un jalon par son ID")
    @GetMapping("/{id}")
    ResponseEntity<JalonResponseDTO> getJalonById(@PathVariable Long id);

    @Operation(summary = "Récupérer les jalons par chantier")
    @GetMapping("/chantier/{chantierId}")
    ResponseEntity<List<JalonResponseDTO>> getJalonsByChantier(@PathVariable Long chantierId);

    @Operation(summary = "Récupérer les jalons par statut")
    @GetMapping("/statut/{statut}")
    ResponseEntity<List<JalonResponseDTO>> getJalonsByStatut(@PathVariable String statut);

    @Operation(summary = "Mettre à jour le statut d'un jalon")
    @PatchMapping("/{id}/statut")
    ResponseEntity<JalonResponseDTO> updateJalonStatut(@PathVariable Long id, @RequestParam String statut);
}

