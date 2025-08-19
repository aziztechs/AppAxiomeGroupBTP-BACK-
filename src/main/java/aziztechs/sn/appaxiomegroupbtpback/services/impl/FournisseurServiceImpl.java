package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FournisseurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FournisseurResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.Fournisseur;
import aziztechs.sn.appaxiomegroupbtpback.exception.ResourceNotFoundException;
import aziztechs.sn.appaxiomegroupbtpback.repositories.FournisseurRepository;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.FournisseurService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final DtoMapper dtoMapper;

    @Override
    @Transactional
    public FournisseurResponseDTO createFournisseur(FournisseurRequestDTO fournisseurDTO) {
        // Mapper le DTO vers l'entité
        Fournisseur fournisseur = dtoMapper.mapToEntity(fournisseurDTO, Fournisseur.class);
        
        // Sauvegarder l'entité
        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);
        
        // Mapper l'entité sauvegardée vers le DTO de réponse
        return dtoMapper.mapToDto(savedFournisseur, FournisseurResponseDTO.class);
    }

    @Override
    @Transactional
    public FournisseurResponseDTO updateFournisseur(Long id, FournisseurRequestDTO fournisseurDTO) {
        // Récupérer le fournisseur existant
        Fournisseur existingFournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur", "id", id));
        
        // Mettre à jour les propriétés
        existingFournisseur.setRaisonSociale(fournisseurDTO.getRaisonSociale());
        existingFournisseur.setAdresse(fournisseurDTO.getAdresse());
        existingFournisseur.setContact(fournisseurDTO.getContact());
        
        if (fournisseurDTO.getNotation() != null) {
            existingFournisseur.setNotation(fournisseurDTO.getNotation());
        }
        
        // Sauvegarder les modifications
        Fournisseur updatedFournisseur = fournisseurRepository.save(existingFournisseur);
        
        // Mapper l'entité mise à jour vers le DTO de réponse
        return dtoMapper.mapToDto(updatedFournisseur, FournisseurResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteFournisseur(Long id) {
        // Vérifier si le fournisseur existe
        if (!fournisseurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fournisseur", "id", id);
        }
        
        // Supprimer le fournisseur
        fournisseurRepository.deleteById(id);
    }

    @Override
    public FournisseurResponseDTO getFournisseurById(Long id) {
        // Récupérer le fournisseur
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur", "id", id));
        
        // Mapper l'entité vers le DTO de réponse
        return dtoMapper.mapToDto(fournisseur, FournisseurResponseDTO.class);
    }

    @Override
    public List<FournisseurResponseDTO> getAllFournisseurs() {
        // Récupérer tous les fournisseurs
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        
        // Mapper les entités vers les DTOs de réponse
        return fournisseurs.stream()
                .map(fournisseur -> dtoMapper.mapToDto(fournisseur, FournisseurResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FournisseurResponseDTO> searchFournisseurs(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllFournisseurs();
        }
        
        // Rechercher par raison sociale
        List<Fournisseur> byRaisonSociale = fournisseurRepository.findByRaisonSocialeContainingIgnoreCase(keyword);
        
        // Rechercher par adresse
        List<Fournisseur> byAdresse = fournisseurRepository.findByAdresseContainingIgnoreCase(keyword);
        
        // Combiner les résultats sans doublons
        Set<Fournisseur> uniqueFournisseurs = new HashSet<>();
        uniqueFournisseurs.addAll(byRaisonSociale);
        uniqueFournisseurs.addAll(byAdresse);
        
        // Mapper les entités vers les DTOs de réponse
        return uniqueFournisseurs.stream()
                .map(fournisseur -> dtoMapper.mapToDto(fournisseur, FournisseurResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FournisseurResponseDTO updateFournisseurNotation(Long id, Integer notation) {
        // Vérifier que la notation est valide
        if (notation < 0 || notation > 5) {
            throw new IllegalArgumentException("La notation doit être comprise entre 0 et 5");
        }
        
        // Récupérer le fournisseur existant
        Fournisseur existingFournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur", "id", id));
        
        // Mettre à jour la notation
        existingFournisseur.setNotation(notation);
        
        // Sauvegarder les modifications
        Fournisseur updatedFournisseur = fournisseurRepository.save(existingFournisseur);
        
        // Mapper l'entité mise à jour vers le DTO de réponse
        return dtoMapper.mapToDto(updatedFournisseur, FournisseurResponseDTO.class);
    }
}

