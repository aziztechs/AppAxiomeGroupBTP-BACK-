package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.BudgetController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.BudgetRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.BudgetResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BudgetControllerImpl implements BudgetController {

    private final BudgetService budgetService;

    @Override
    public ResponseEntity<BudgetResponseDTO> createBudget(BudgetRequestDTO budgetDTO) {
        BudgetResponseDTO createdBudget = budgetService.createBudget(budgetDTO);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BudgetResponseDTO> updateBudget(Long id, BudgetRequestDTO budgetDTO) {
        BudgetResponseDTO updatedBudget = budgetService.updateBudget(id, budgetDTO);
        return ResponseEntity.ok(updatedBudget);
    }

    @Override
    public ResponseEntity<BudgetResponseDTO> getBudgetById(Long id) {
        BudgetResponseDTO budget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budget);
    }

    @Override
    public ResponseEntity<List<BudgetResponseDTO>> getAllBudgets() {
        List<BudgetResponseDTO> budgets = budgetService.getAllBudgets();
        return ResponseEntity.ok(budgets);
    }

    @Override
    public ResponseEntity<BudgetResponseDTO> updateBudgetMontants(Long id, Double montantEngage, Double montantRealise) {
        BudgetResponseDTO updatedBudget = budgetService.updateBudgetMontants(id, montantEngage, montantRealise);
        return ResponseEntity.ok(updatedBudget);
    }

    @Override
    public ResponseEntity<Double> getTotalBudgetPrevu() {
        Double total = budgetService.getTotalBudgetPrevu();
        return ResponseEntity.ok(total);
    }

    @Override
    public ResponseEntity<Double> getTotalBudgetEngage() {
        Double total = budgetService.getTotalBudgetEngage();
        return ResponseEntity.ok(total);
    }

    @Override
    public ResponseEntity<Double> getTotalBudgetRealise() {
        Double total = budgetService.getTotalBudgetRealise();
        return ResponseEntity.ok(total);
    }
}

