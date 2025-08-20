package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;


import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.DocumentController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.DocumentRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.DocumentResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class DocumentControllerImpl implements DocumentController {

    private final DocumentService documentService;

    public DocumentControllerImpl(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public ResponseEntity<DocumentResponseDTO> uploadDocument(
            @RequestParam Long chantierId,
            @RequestParam String type,
            @RequestParam MultipartFile file) {
        DocumentRequestDTO documentDTO = new DocumentRequestDTO();
        documentDTO.setType(type);
        documentDTO.setChantierId(chantierId);
        DocumentResponseDTO response = documentService.uploadDocument(documentDTO, file);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        byte[] fileContent = documentService.downloadDocument(id);
        return ResponseEntity.ok()
                .header("Content-Type", "application/octet-stream")
                .header("Content-Disposition", "attachment; filename=\"" + id + "\"")
                .body(fileContent);
    }

    @Override
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<DocumentResponseDTO>> getDocumentsByChantier(@PathVariable Long chantierId) {
        List<DocumentResponseDTO> response = documentService.getDocumentsByChantier(chantierId);
        return ResponseEntity.ok(response);
    }
}
