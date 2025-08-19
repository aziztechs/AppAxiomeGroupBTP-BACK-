package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FactureRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.EcritureComptableResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FactureResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.Facture;
import aziztechs.sn.appaxiomegroupbtpback.exception.ResourceNotFoundException;
import aziztechs.sn.appaxiomegroupbtpback.repositories.FactureRepository;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.EcritureComptableService;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.FactureService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final EcritureComptableService ecritureComptableService;
    private final DtoMapper dtoMapper;

    @Override
    @Transactional
    public FactureResponseDTO createFacture(FactureRequestDTO factureDTO) {
        // Vérifier si une facture avec le même numéro existe déjà
        factureRepository.findByNumero(factureDTO.getNumero())
                .ifPresent(facture -> {
                    throw new IllegalArgumentException("Une facture avec le numéro " + factureDTO.getNumero() + " existe déjà");
                });

        // Créer une nouvelle facture
        Facture facture = new Facture();
        facture.setNumero(factureDTO.getNumero());
        facture.setMontant(factureDTO.getMontant());
        
        // Si le statut n'est pas fourni, définir "EN_ATTENTE" par défaut
        if (factureDTO.getStatut() == null || factureDTO.getStatut().isEmpty()) {
            facture.setStatut("EN_ATTENTE");
        } else {
            facture.setStatut(factureDTO.getStatut());
        }
        
        // Si la date n'est pas fournie, utiliser la date actuelle
        if (factureDTO.getDate() == null) {
            facture.setDate(LocalDate.now());
        } else {
            facture.setDate(factureDTO.getDate());
        }
        
        // Sauvegarder la facture
        Facture savedFacture = factureRepository.save(facture);
        
        // Convertir en DTO de réponse
        return mapToResponseDTO(savedFacture);
    }

    @Override
    @Transactional
    public FactureResponseDTO updateFacture(Long id, FactureRequestDTO factureDTO) {
        // Récupérer la facture existante
        Facture existingFacture = factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture", "id", id));
        
        // Vérifier si le numéro de facture est déjà utilisé par une autre facture
        if (!existingFacture.getNumero().equals(factureDTO.getNumero())) {
            factureRepository.findByNumero(factureDTO.getNumero())
                    .ifPresent(facture -> {
                        throw new IllegalArgumentException("Une facture avec le numéro " + factureDTO.getNumero() + " existe déjà");
                    });
        }
        
        // Mettre à jour les propriétés
        existingFacture.setNumero(factureDTO.getNumero());
        existingFacture.setMontant(factureDTO.getMontant());
        
        if (factureDTO.getStatut() != null && !factureDTO.getStatut().isEmpty()) {
            existingFacture.setStatut(factureDTO.getStatut());
        }
        
        if (factureDTO.getDate() != null) {
            existingFacture.setDate(factureDTO.getDate());
        }
        
        // Sauvegarder les modifications
        Facture updatedFacture = factureRepository.save(existingFacture);
        
        // Convertir en DTO de réponse
        return mapToResponseDTO(updatedFacture);
    }

    @Override
    @Transactional
    public void deleteFacture(Long id) {
        // Vérifier si la facture existe
        if (!factureRepository.existsById(id)) {
            throw new ResourceNotFoundException("Facture", "id", id);
        }
        
        // Supprimer la facture
        factureRepository.deleteById(id);
    }

    @Override
    public FactureResponseDTO getFactureById(Long id) {
        // Récupérer la facture
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture", "id", id));
        
        // Convertir en DTO de réponse
        return mapToResponseDTO(facture);
    }

    @Override
    public List<FactureResponseDTO> getAllFactures() {
        // Récupérer toutes les factures
        List<Facture> factures = factureRepository.findAll();
        
        // Convertir en DTOs de réponse
        return factures.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FactureResponseDTO> getFacturesByStatut(String statut) {
        // Récupérer les factures par statut
        List<Facture> factures = factureRepository.findByStatut(statut);
        
        // Convertir en DTOs de réponse
        return factures.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FactureResponseDTO updateFactureStatut(Long id, String statut) {
        // Récupérer la facture existante
        Facture existingFacture = factureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facture", "id", id));
        
        // Mettre à jour le statut
        existingFacture.setStatut(statut);
        
        // Sauvegarder les modifications
        Facture updatedFacture = factureRepository.save(existingFacture);
        
        // Convertir en DTO de réponse
        return mapToResponseDTO(updatedFacture);
    }

    @Override
    public Double getTotalFacturesPayees() {
        // Récupérer le total des factures payées
        Double total = factureRepository.getTotalFacturesPayees();
        return total != null ? total : 0.0;
    }
    
    /**
     * Méthode utilitaire pour mapper une entité Facture vers un DTO de réponse
     */
    private FactureResponseDTO mapToResponseDTO(Facture facture) {
        FactureResponseDTO responseDTO = dtoMapper.mapToDto(facture, FactureResponseDTO.class);
        
        // Récupérer les écritures comptables associées à cette facture
        if (facture.getId() != null) {
            List<EcritureComptableResponseDTO> ecritures = ecritureComptableService.getEcrituresByFacture(facture.getId());
            responseDTO.setEcrituresComptables(ecritures);
        }
        
        return responseDTO;
    }
}

