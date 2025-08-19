package aziztechs.sn.appaxiomegroupbtpback.services.impl;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.DocumentRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.DocumentResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.entities.Chantier;
import aziztechs.sn.appaxiomegroupbtpback.entities.Document;
import aziztechs.sn.appaxiomegroupbtpback.exception.ResourceNotFoundException;
import aziztechs.sn.appaxiomegroupbtpback.repositories.ChantierRepository;
import aziztechs.sn.appaxiomegroupbtpback.repositories.DocumentRepository;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.DocumentService;
import aziztechs.sn.appaxiomegroupbtpback.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ChantierRepository chantierRepository;
    private final DtoMapper dtoMapper;
    
    private final String uploadDir = "uploads/documents";

    @Override
    @Transactional
    public DocumentResponseDTO uploadDocument(DocumentRequestDTO documentDTO, MultipartFile file) {
        try {
            // Vérifier si le chantier existe
            Chantier chantier = chantierRepository.findById(documentDTO.getChantierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Chantier", "id", documentDTO.getChantierId()));

            // Créer le répertoire de stockage s'il n'existe pas
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Générer un nom de fichier unique
            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;
            
            // Chemin complet du fichier
            Path filePath = uploadPath.resolve(uniqueFilename);
            
            // Enregistrer le fichier
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Créer l'entité Document
            Document document = new Document();
            document.setType(documentDTO.getType());
            document.setChantier(chantier);
            document.setCheminFichier(filePath.toString());
            document.setDateUpload(LocalDateTime.now());

            // Sauvegarder dans la base de données
            Document savedDocument = documentRepository.save(document);
            
            // Mapper vers DTO de réponse
            DocumentResponseDTO responseDTO = dtoMapper.mapToDto(savedDocument, DocumentResponseDTO.class);
            responseDTO.setNomFichier(originalFilename);
            responseDTO.setChantierId(chantier.getId());
            responseDTO.setChantierReference(chantier.getReference());
            
            return responseDTO;
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du fichier: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document", "id", id));
        
        try {
            // Supprimer le fichier physique
            Path filePath = Paths.get(document.getCheminFichier());
            Files.deleteIfExists(filePath);
            
            // Supprimer l'entrée de la base de données
            documentRepository.delete(document);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la suppression du fichier: " + e.getMessage());
        }
    }

    @Override
    public DocumentResponseDTO getDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document", "id", id));
        
        DocumentResponseDTO responseDTO = dtoMapper.mapToDto(document, DocumentResponseDTO.class);
        
        // Extraire le nom du fichier du chemin
        String cheminFichier = document.getCheminFichier();
        String nomFichier = cheminFichier.substring(cheminFichier.lastIndexOf("/") + 1);
        responseDTO.setNomFichier(nomFichier);
        
        // Ajouter les informations du chantier
        if (document.getChantier() != null) {
            responseDTO.setChantierId(document.getChantier().getId());
            responseDTO.setChantierReference(document.getChantier().getReference());
        }
        
        return responseDTO;
    }

    @Override
    public List<DocumentResponseDTO> getDocumentsByChantier(Long chantierId) {
        // Vérifier si le chantier existe
        if (!chantierRepository.existsById(chantierId)) {
            throw new ResourceNotFoundException("Chantier", "id", chantierId);
        }
        
        List<Document> documents = documentRepository.findByChantierId(chantierId);
        
        return documents.stream()
                .map(document -> {
                    DocumentResponseDTO dto = dtoMapper.mapToDto(document, DocumentResponseDTO.class);
                    
                    // Extraire le nom du fichier du chemin
                    String cheminFichier = document.getCheminFichier();
                    String nomFichier = cheminFichier.substring(cheminFichier.lastIndexOf("/") + 1);
                    dto.setNomFichier(nomFichier);
                    
                    // Ajouter les informations du chantier
                    if (document.getChantier() != null) {
                        dto.setChantierId(document.getChantier().getId());
                        dto.setChantierReference(document.getChantier().getReference());
                    }
                    
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<DocumentResponseDTO> getDocumentsByType(String type) {
        List<Document> documents = documentRepository.findByType(type);
        
        return documents.stream()
                .map(document -> {
                    DocumentResponseDTO dto = dtoMapper.mapToDto(document, DocumentResponseDTO.class);
                    
                    // Extraire le nom du fichier du chemin
                    String cheminFichier = document.getCheminFichier();
                    String nomFichier = cheminFichier.substring(cheminFichier.lastIndexOf("/") + 1);
                    dto.setNomFichier(nomFichier);
                    
                    // Ajouter les informations du chantier
                    if (document.getChantier() != null) {
                        dto.setChantierId(document.getChantier().getId());
                        dto.setChantierReference(document.getChantier().getReference());
                    }
                    
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public byte[] downloadDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document", "id", id));
        
        try {
            Path filePath = Paths.get(document.getCheminFichier());
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier: " + e.getMessage());
        }
    }
}

