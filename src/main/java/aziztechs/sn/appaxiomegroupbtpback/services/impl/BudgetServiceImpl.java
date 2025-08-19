package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.BudgetRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.BudgetResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.Budget;
import aziztechs.sn.appaxiomegroupbtpback.exception.ResourceNotFoundException;
import aziztechs.sn.appaxiomegroupbtpback.repositories.BudgetRepository;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.BudgetService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final DtoMapper dtoMapper;

    @Override
    @Transactional
    public BudgetResponseDTO createBudget(BudgetRequestDTO budgetDTO) {
        // Mapper le DTO vers l'entité
        Budget budget = dtoMapper.mapToEntity(budgetDTO, Budget.class);
        
        // Sauvegarder l'entité
        Budget savedBudget = budgetRepository.save(budget);
        
        // Mapper l'entité sauvegardée vers le DTO de réponse
        BudgetResponseDTO responseDTO = dtoMapper.mapToDto(savedBudget, BudgetResponseDTO.class);
        
        // Calculer les taux d'engagement et de réalisation
        calculateRates(responseDTO);
        
        return responseDTO;
    }

    @Override
    @Transactional
    public BudgetResponseDTO updateBudget(Long id, BudgetRequestDTO budgetDTO) {
        // Récupérer le budget existant
        Budget existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget", "id", id));
        
        // Mettre à jour les propriétés
        existingBudget.setMontantPrevu(budgetDTO.getMontantPrevu());
        existingBudget.setMontantEngage(budgetDTO.getMontantEngage());
        existingBudget.setMontantRealise(budgetDTO.getMontantRealise());
        
        // Sauvegarder les modifications
        Budget updatedBudget = budgetRepository.save(existingBudget);
        
        // Mapper l'entité mise à jour vers le DTO de réponse
        BudgetResponseDTO responseDTO = dtoMapper.mapToDto(updatedBudget, BudgetResponseDTO.class);
        
        // Calculer les taux d'engagement et de réalisation
        calculateRates(responseDTO);
        
        return responseDTO;
    }

    @Override
    public BudgetResponseDTO getBudgetById(Long id) {
        // Récupérer le budget
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget", "id", id));
        
        // Mapper l'entité vers le DTO de réponse
        BudgetResponseDTO responseDTO = dtoMapper.mapToDto(budget, BudgetResponseDTO.class);
        
        // Calculer les taux d'engagement et de réalisation
        calculateRates(responseDTO);
        
        return responseDTO;
    }

    @Override
    public List<BudgetResponseDTO> getAllBudgets() {
        // Récupérer tous les budgets
        List<Budget> budgets = budgetRepository.findAll();
        
        // Mapper les entités vers les DTOs de réponse
        return budgets.stream()
                .map(budget -> {
                    BudgetResponseDTO responseDTO = dtoMapper.mapToDto(budget, BudgetResponseDTO.class);
                    calculateRates(responseDTO);
                    return responseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BudgetResponseDTO updateBudgetMontants(Long id, Double montantEngage, Double montantRealise) {
        // Récupérer le budget existant
        Budget existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget", "id", id));
        
        // Mettre à jour les montants si fournis
        if (montantEngage != null) {
            existingBudget.setMontantEngage(montantEngage);
        }
        
        if (montantRealise != null) {
            existingBudget.setMontantRealise(montantRealise);
        }
        
        // Sauvegarder les modifications
        Budget updatedBudget = budgetRepository.save(existingBudget);
        
        // Mapper l'entité mise à jour vers le DTO de réponse
        BudgetResponseDTO responseDTO = dtoMapper.mapToDto(updatedBudget, BudgetResponseDTO.class);
        
        // Calculer les taux d'engagement et de réalisation
        calculateRates(responseDTO);
        
        return responseDTO;
    }

    @Override
    public Double getTotalBudgetPrevu() {
        Double total = budgetRepository.getTotalBudgetPrevu();
        return total != null ? total : 0.0;
    }

    @Override
    public Double getTotalBudgetEngage() {
        Double total = budgetRepository.getTotalBudgetEngage();
        return total != null ? total : 0.0;
    }

    @Override
    public Double getTotalBudgetRealise() {
        // Cette méthode n'est pas directement implémentée dans le repository, donc nous la calculons ici
        Double total = budgetRepository.findAll().stream()
                .mapToDouble(Budget::getMontantRealise)
                .sum();
        return total;
    }
    
    /**
     * Méthode utilitaire pour calculer les taux d'engagement et de réalisation
     */
    private void calculateRates(BudgetResponseDTO budget) {
        if (budget.getMontantPrevu() > 0) {
            budget.setTauxEngagement((budget.getMontantEngage() / budget.getMontantPrevu()) * 100);
            budget.setTauxRealisation((budget.getMontantRealise() / budget.getMontantPrevu()) * 100);
        } else {
            budget.setTauxEngagement(0);
            budget.setTauxRealisation(0);
        }
    }
}

