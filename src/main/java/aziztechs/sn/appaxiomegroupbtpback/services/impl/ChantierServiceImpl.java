package aziztechs.sn.appaxiomegroupbtpback.services.impl;



import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.ChantierRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ChantierResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.Budget;
import aziztechs.sn.appaxiomegroupbtpback.entities.Chantier;
import aziztechs.sn.appaxiomegroupbtpback.repositories.BudgetRepository;
import aziztechs.sn.appaxiomegroupbtpback.repositories.ChantierRepository;
import aziztechs.sn.appaxiomegroupbtpback.repositories.FactureRepository;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.ChantierService;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.DocumentService;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.IncidentService;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.JalonService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChantierServiceImpl implements ChantierService {

    private final ChantierRepository chantierRepository;
    private final JalonService jalonService;
    private final DocumentService documentService;
    private final IncidentService incidentService;
    private final DtoMapper dtoMapper;
    private final BudgetRepository budgetRepository;
    private final FactureRepository factureRepository;

    public ChantierServiceImpl(ChantierRepository chantierRepository,
                               JalonService jalonService,
                               DocumentService documentService,
                               IncidentService incidentService,
                               DtoMapper dtoMapper,
                               BudgetRepository budgetRepository,
                               FactureRepository factureRepository) {
        this.chantierRepository = chantierRepository;
        this.jalonService = jalonService;
        this.documentService = documentService;
        this.incidentService = incidentService;
        this.dtoMapper = dtoMapper;
        this.budgetRepository = budgetRepository;
        this.factureRepository = factureRepository;
    }

    @Transactional
    @Override
    public ChantierResponseDTO createChantier(ChantierRequestDTO chantierDTO) {
        Chantier chantier = dtoMapper.mapToEntity(chantierDTO, Chantier.class);
        chantier.setStatut("PLANIFIE");

        // Création automatique du budget associé
        Budget budget = new Budget();
        budget.setMontantPrevu(chantierDTO.getBudgetInitial());
        budget.setMontantEngage(0.0);
        budget.setMontantRealise(0.0);
        budgetRepository.save(budget);

        chantier.setBudget(budget);
        Chantier savedChantier = chantierRepository.save(chantier);

        return dtoMapper.mapToDto(savedChantier, ChantierResponseDTO.class);
    }

    @Override
    @Transactional
    public ChantierResponseDTO updateChantier(Long id, ChantierRequestDTO chantierDTO) {
        Chantier chantier = chantierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chantier", "id", id));

        chantier.setReference(chantierDTO.getReference());
        chantier.setClient(chantierDTO.getClient());
        chantier.setLocalisation(chantierDTO.getLocalisation());
        chantier.setDateDebut(chantierDTO.getDateDebut());
        chantier.setDateFin(chantierDTO.getDateFin());

        // Mise à jour du budget si nécessaire
        if(chantierDTO.getBudgetInitial() != null &&
                !chantierDTO.getBudgetInitial().equals(chantier.getBudget().getMontantPrevu())) {
            Budget budget = chantier.getBudget();
            budget.setMontantPrevu(chantierDTO.getBudgetInitial());
            budgetRepository.save(budget);
        }

        updateChantierStatut(chantier);

        Chantier updatedChantier = chantierRepository.save(chantier);
        return dtoMapper.mapToDto(updatedChantier, ChantierResponseDTO.class);
    }

    private void updateChantierStatut(Chantier chantier) {
        LocalDate today = LocalDate.now();

        if(chantier.getDateDebut() == null || chantier.getDateFin() == null) {
            chantier.setStatut("NON_PLANIFIE");
            return;
        }

        if(today.isBefore(chantier.getDateDebut())) {
            chantier.setStatut("PLANIFIE");
        } else if(today.isAfter(chantier.getDateFin())) {
            chantier.setStatut("TERMINE");
            // Calcul du taux de réalisation final
            Budget budget = chantier.getBudget();
            if(budget != null) {
                budget.setMontantRealise(factureRepository.sumMontantByChantier(chantier.getId()));
                budgetRepository.save(budget);
            }
        } else {
            chantier.setStatut("EN_COURS");
        }
    }

    @Override
    @Transactional
    public void deleteChantier(Long id) {
        Chantier chantier = chantierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chantier", "id", id));

        // Désactivation plutôt que suppression
        chantier.setStatut("ANNULE");
        chantierRepository.save(chantier);
    }

    @Override
    public ChantierResponseDTO getChantierById(Long id) {
        Chantier chantier = chantierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chantier", "id", id));

        return enrichChantierResponse(chantier);
    }

    @Override
    public ChantierDetailResponseDTO getChantierDetailsById(Long id) {
        Chantier chantier = chantierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chantier", "id", id));

        ChantierDetailResponseDTO response = dtoMapper.mapToDto(chantier, ChantierDetailResponseDTO.class);
        response.setJalons(jalonService.getJalonsByChantier(id));
        response.setDocuments(documentService.getDocumentsByChantier(id));
        response.setIncidents(incidentService.getIncidentsByChantier(id));
        response.setBudget(dtoMapper.mapToDto(chantier.getBudget(), BudgetResponseDTO.class));

        return response;
    }

    private ChantierResponseDTO enrichChantierResponse(Chantier chantier) {
        ChantierResponseDTO response = dtoMapper.mapToDto(chantier, ChantierResponseDTO.class);

        // Calcul des indicateurs
        if(chantier.getBudget() != null) {
            double tauxEngagement = chantier.getBudget().getMontantPrevu() > 0 ?
                    (chantier.getBudget().getMontantEngage() / chantier.getBudget().getMontantPrevu()) * 100 : 0;
            double tauxRealisation = chantier.getBudget().getMontantPrevu() > 0 ?
                    (chantier.getBudget().getMontantRealise() / chantier.getBudget().getMontantPrevu()) * 100 : 0;

            response.setTauxEngagement(tauxEngagement);
            response.setTauxRealisation(tauxRealisation);
        }

        return response;
    }

    @Override
    public List<ChantierResponseDTO> getAllChantiers() {
        return chantierRepository.findAll().stream()
                .map(this::enrichChantierResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChantierResponseDTO> getChantiersByStatut(String statut) {
        return chantierRepository.findByStatut(statut).stream()
                .map(this::enrichChantierResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChantierResponseDTO> getChantiersEnRetard() {
        return chantierRepository.findByDateFinBeforeAndStatutNot(LocalDate.now(), "TERMINE").stream()
                .map(this::enrichChantierResponse)
                .collect(Collectors.toList());
    }
}