package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.EcritureComptableRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.EcritureComptableResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.EcritureComptable;
import aziztechs.sn.appaxiomegroupbtpback.entities.Facture;
import aziztechs.sn.appaxiomegroupbtpback.exception.ResourceNotFoundException;
import aziztechs.sn.appaxiomegroupbtpback.repositories.EcritureComptableRepository;
import aziztechs.sn.appaxiomegroupbtpback.repositories.FactureRepository;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.EcritureComptableService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EcritureComptableServiceImpl implements EcritureComptableService {

    private final EcritureComptableRepository ecritureComptableRepository;
    private final FactureRepository factureRepository;
    private final DtoMapper dtoMapper;

    @Override
    @Transactional
    public EcritureComptableResponseDTO createEcritureComptable(EcritureComptableRequestDTO ecritureDTO) {
        // Créer une nouvelle écriture comptable
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setType(ecritureDTO.getType());
        ecritureComptable.setMontant(ecritureDTO.getMontant());
        ecritureComptable.setCompte(ecritureDTO.getCompte());
        
        // Si la date n'est pas fournie, utiliser la date actuelle
        if (ecritureDTO.getDate() == null) {
            ecritureComptable.setDate(LocalDate.now());
        } else {
            ecritureComptable.setDate(ecritureDTO.getDate());
        }
        
        // Associer à une facture si l'ID est fourni
        if (ecritureDTO.getFactureId() != null) {
            Facture facture = factureRepository.findById(ecritureDTO.getFactureId())
                    .orElseThrow(() -> new ResourceNotFoundException("Facture", "id", ecritureDTO.getFactureId()));
            ecritureComptable.setFacture(facture);
        }
        
        // Sauvegarder l'écriture comptable
        EcritureComptable savedEcriture = ecritureComptableRepository.save(ecritureComptable);
        
        // Convertir en DTO de réponse
        return mapToResponseDTO(savedEcriture);
    }

    @Override
    @Transactional
    public EcritureComptableResponseDTO updateEcritureComptable(Long id, EcritureComptableRequestDTO ecritureDTO) {
        // Récupérer l'écriture comptable existante
        EcritureComptable existingEcriture = ecritureComptableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EcritureComptable", "id", id));
        
        // Mettre à jour les propriétés
        existingEcriture.setType(ecritureDTO.getType());
        existingEcriture.setMontant(ecritureDTO.getMontant());
        existingEcriture.setCompte(ecritureDTO.getCompte());
        
        if (ecritureDTO.getDate() != null) {
            existingEcriture.setDate(ecritureDTO.getDate());
        }
        
        // Mettre à jour l'association avec la facture si nécessaire
        if (ecritureDTO.getFactureId() != null) {
            Facture facture = factureRepository.findById(ecritureDTO.getFactureId())
                    .orElseThrow(() -> new ResourceNotFoundException("Facture", "id", ecritureDTO.getFactureId()));
            existingEcriture.setFacture(facture);
        } else {
            existingEcriture.setFacture(null);
        }
        
        // Sauvegarder les modifications
        EcritureComptable updatedEcriture = ecritureComptableRepository.save(existingEcriture);
        
        // Convertir en DTO de réponse
        return mapToResponseDTO(updatedEcriture);
    }

    @Override
    @Transactional
    public void deleteEcritureComptable(Long id) {
        // Vérifier si l'écriture comptable existe
        if (!ecritureComptableRepository.existsById(id)) {
            throw new ResourceNotFoundException("EcritureComptable", "id", id);
        }
        
        // Supprimer l'écriture comptable
        ecritureComptableRepository.deleteById(id);
    }

    @Override
    public EcritureComptableResponseDTO getEcritureComptableById(Long id) {
        // Récupérer l'écriture comptable
        EcritureComptable ecritureComptable = ecritureComptableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EcritureComptable", "id", id));
        
        // Convertir en DTO de réponse
        return mapToResponseDTO(ecritureComptable);
    }

    @Override
    public List<EcritureComptableResponseDTO> getEcrituresByFacture(Long factureId) {
        // Vérifier si la facture existe
        if (!factureRepository.existsById(factureId)) {
            throw new ResourceNotFoundException("Facture", "id", factureId);
        }
        
        // Récupérer les écritures comptables associées à la facture
        List<EcritureComptable> ecritures = ecritureComptableRepository.findByFactureId(factureId);
        
        // Convertir en DTOs de réponse
        return ecritures.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EcritureComptableResponseDTO> getEcrituresByType(String type) {
        // Récupérer les écritures comptables par type
        List<EcritureComptable> ecritures = ecritureComptableRepository.findByType(type);
        
        // Convertir en DTOs de réponse
        return ecritures.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalByType(String type) {
        // Récupérer le total des montants par type
        Double total = ecritureComptableRepository.getTotalByType(type);
        return total != null ? total : 0.0;
    }
    
    /**
     * Méthode utilitaire pour mapper une entité EcritureComptable vers un DTO de réponse
     */
    private EcritureComptableResponseDTO mapToResponseDTO(EcritureComptable ecritureComptable) {
        EcritureComptableResponseDTO responseDTO = dtoMapper.mapToDto(ecritureComptable, EcritureComptableResponseDTO.class);
        
        // Ajouter les informations de la facture si elle existe
        if (ecritureComptable.getFacture() != null) {
            responseDTO.setFactureId(ecritureComptable.getFacture().getId());
            responseDTO.setFactureNumero(ecritureComptable.getFacture().getNumero());
        }
        
        return responseDTO;
    }
}

