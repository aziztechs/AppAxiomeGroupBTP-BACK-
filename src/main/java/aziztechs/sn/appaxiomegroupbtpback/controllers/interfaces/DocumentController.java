package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;


import aziztechs.sn.appaxiomegroupbtpback.dto.response.DocumentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Documents", description = "API pour la gestion des documents")
@RequestMapping("/api/documents")
public interface DocumentController {

    @Operation(summary = "Téléverser un document")
    @PostMapping("/upload")
    ResponseEntity<DocumentResponseDTO> uploadDocument(
            @RequestParam Long chantierId,
            @RequestParam String type,
            @RequestParam MultipartFile file);

    @Operation(summary = "Télécharger un document")
    @GetMapping("/download/{id}")
    ResponseEntity<byte[]> downloadDocument(@PathVariable Long id);

    @Operation(summary = "Supprimer un document")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDocument(@PathVariable Long id);

    @Operation(summary = "Lister les documents d'un chantier")
    @GetMapping("/chantier/{chantierId}")
    ResponseEntity<List<DocumentResponseDTO>> getDocumentsByChantier(@PathVariable Long chantierId);
}
