package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.DaRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.DaResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.DA;
import aziztechs.sn.appaxiomegroupbtpback.exception.ResourceNotFoundException;
import aziztechs.sn.appaxiomegroupbtpback.repositories.DaRepository;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.DaService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DaServiceImpl implements DaService {

    private final DaRepository daRepository;
    private final DtoMapper dtoMapper;

    @Override
    @Transactional
    public DaResponseDTO createDa(DaRequestDTO daDTO) {
        // Vérifier si la référence existe déjà
        if (daRepository.findByReference(daDTO.getReference()).isPresent()) {
            throw new RuntimeException("Une DA avec cette référence existe déjà");
        }

        // Mapper le DTO vers l'entité
        DA da = dtoMapper.mapToEntity(daDTO, DA.class);
        
        // Si la date d'émission n'est pas fournie, utiliser la date actuelle
        if (da.getDateEmission() == null) {
            da.setDateEmission(LocalDate.now());
        }
        
        // Si le statut n'est pas fourni, définir "En attente" par défaut
        if (da.getStatut() == null || da.getStatut().isEmpty()) {
            da.setStatut("En attente");
        }

        // Sauvegarder l'entité
        DA savedDa = daRepository.save(da);

        // Mapper l'entité sauvegardée vers le DTO de réponse
        return dtoMapper.mapToDto(savedDa, DaResponseDTO.class);
    }

    @Override
    @Transactional
    public DaResponseDTO updateDa(Long id, DaRequestDTO daDTO) {
        // Récupérer la DA existante
        DA existingDa = daRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DA", "id", id));

        // Vérifier si la nouvelle référence existe déjà pour une autre DA
        if (!existingDa.getReference().equals(daDTO.getReference()) &&
                daRepository.findByReference(daDTO.getReference()).isPresent()) {
            throw new RuntimeException("Une DA avec cette référence existe déjà");
        }

        // Mettre à jour les propriétés
        existingDa.setReference(daDTO.getReference());
        
        if (daDTO.getDateEmission() != null) {
            existingDa.setDateEmission(daDTO.getDateEmission());
        }
        
        if (daDTO.getStatut() != null && !daDTO.getStatut().isEmpty()) {
            existingDa.setStatut(daDTO.getStatut());
        }
        
        existingDa.setMontant(daDTO.getMontant());

        // Sauvegarder les modifications
        DA updatedDa = daRepository.save(existingDa);

        // Mapper l'entité mise à jour vers le DTO de réponse
        return dtoMapper.mapToDto(updatedDa, DaResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteDa(Long id) {
        // Vérifier si la DA existe
        if (!daRepository.existsById(id)) {
            throw new ResourceNotFoundException("DA", "id", id);
        }
        
        // Supprimer la DA
        daRepository.deleteById(id);
    }

    @Override
    public DaResponseDTO getDaById(Long id) {
        // Récupérer la DA
        DA da = daRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DA", "id", id));
        
        // Mapper l'entité vers le DTO de réponse
        return dtoMapper.mapToDto(da, DaResponseDTO.class);
    }

    @Override
    public List<DaResponseDTO> getAllDas() {
        // Récupérer toutes les DAs
        List<DA> das = daRepository.findAll();
        
        // Mapper les entités vers les DTOs de réponse
        return das.stream()
                .map(da -> dtoMapper.mapToDto(da, DaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DaResponseDTO> getDasByStatut(String statut) {
        // Récupérer les DAs par statut
        List<DA> das = daRepository.findByStatut(statut);
        
        // Mapper les entités vers les DTOs de réponse
        return das.stream()
                .map(da -> dtoMapper.mapToDto(da, DaResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DaResponseDTO updateDaStatut(Long id, String statut) {
        // Récupérer la DA existante
        DA existingDa = daRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DA", "id", id));
        
        // Mettre à jour le statut
        existingDa.setStatut(statut);
        
        // Sauvegarder les modifications
        DA updatedDa = daRepository.save(existingDa);
        
        // Mapper l'entité mise à jour vers le DTO de réponse
        return dtoMapper.mapToDto(updatedDa, DaResponseDTO.class);
    }
}

