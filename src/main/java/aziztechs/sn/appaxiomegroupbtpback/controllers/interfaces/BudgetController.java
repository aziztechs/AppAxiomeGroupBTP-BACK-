package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.BudgetRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.BudgetResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Budgets", description = "API pour la gestion des budgets")
@RequestMapping("/api/budgets")
public interface BudgetController {

    @Operation(summary = "Créer un nouveau budget")
    @PostMapping
    ResponseEntity<BudgetResponseDTO> createBudget(@Valid @RequestBody BudgetRequestDTO budgetDTO);

    @Operation(summary = "Mettre à jour un budget")
    @PutMapping("/{id}")
    ResponseEntity<BudgetResponseDTO> updateBudget(@PathVariable Long id, @Valid @RequestBody BudgetRequestDTO budgetDTO);

    @Operation(summary = "Récupérer un budget par son ID")
    @GetMapping("/{id}")
    ResponseEntity<BudgetResponseDTO> getBudgetById(@PathVariable Long id);

    @Operation(summary = "Récupérer tous les budgets")
    @GetMapping
    ResponseEntity<List<BudgetResponseDTO>> getAllBudgets();

    @Operation(summary = "Mettre à jour les montants d'un budget")
    @PatchMapping("/{id}/montants")
    ResponseEntity<BudgetResponseDTO> updateBudgetMontants(
            @PathVariable Long id,
            @RequestParam(required = false) Double montantEngage,
            @RequestParam(required = false) Double montantRealise);

    @Operation(summary = "Récupérer le total des budgets prévus")
    @GetMapping("/total/prevu")
    ResponseEntity<Double> getTotalBudgetPrevu();

    @Operation(summary = "Récupérer le total des budgets engagés")
    @GetMapping("/total/engage")
    ResponseEntity<Double> getTotalBudgetEngage();

    @Operation(summary = "Récupérer le total des budgets réalisés")
    @GetMapping("/total/realise")
    ResponseEntity<Double> getTotalBudgetRealise();
}

