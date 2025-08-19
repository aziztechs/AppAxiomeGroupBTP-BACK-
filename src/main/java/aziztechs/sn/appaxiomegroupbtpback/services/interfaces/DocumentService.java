package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.DocumentRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.DocumentResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    DocumentResponseDTO uploadDocument(DocumentRequestDTO documentDTO, MultipartFile file);
    void deleteDocument(Long id);
    DocumentResponseDTO getDocumentById(Long id);
    List<DocumentResponseDTO> getDocumentsByChantier(Long chantierId);
    List<DocumentResponseDTO> getDocumentsByType(String type);
    byte[] downloadDocument(Long id);
}
