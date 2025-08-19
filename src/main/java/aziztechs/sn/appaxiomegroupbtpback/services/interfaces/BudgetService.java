package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.BudgetRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.BudgetResponseDTO;

import java.util.List;

public interface BudgetService {
    BudgetResponseDTO createBudget(BudgetRequestDTO budgetDTO);
    BudgetResponseDTO updateBudget(Long id, BudgetRequestDTO budgetDTO);
    BudgetResponseDTO getBudgetById(Long id);
    List<BudgetResponseDTO> getAllBudgets();
    BudgetResponseDTO updateBudgetMontants(Long id, Double montantEngage, Double montantRealise);
    Double getTotalBudgetPrevu();
    Double getTotalBudgetEngage();
    Double getTotalBudgetRealise();
}
