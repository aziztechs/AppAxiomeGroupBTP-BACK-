package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.ReceptionController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.ReceptionRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ReceptionResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.ReceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReceptionControllerImpl implements ReceptionController {

    private final ReceptionService receptionService;

    @Override
    public ResponseEntity<ReceptionResponseDTO> createReception(ReceptionRequestDTO receptionDTO) {
        ReceptionResponseDTO createdReception = receptionService.createReception(receptionDTO);
        return new ResponseEntity<>(createdReception, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReceptionResponseDTO> updateReception(Long id, ReceptionRequestDTO receptionDTO) {
        ReceptionResponseDTO updatedReception = receptionService.updateReception(id, receptionDTO);
        return ResponseEntity.ok(updatedReception);
    }

    @Override
    public ResponseEntity<ReceptionResponseDTO> getReceptionById(Long id) {
        ReceptionResponseDTO reception = receptionService.getReceptionById(id);
        return ResponseEntity.ok(reception);
    }

    @Override
    public ResponseEntity<List<ReceptionResponseDTO>> getAllReceptions() {
        List<ReceptionResponseDTO> receptions = receptionService.getAllReceptions();
        return ResponseEntity.ok(receptions);
    }

    @Override
    public ResponseEntity<List<ReceptionResponseDTO>> getReceptionsByConformite(Boolean conformite) {
        List<ReceptionResponseDTO> receptions = receptionService.getReceptionsByConformite(conformite);
        return ResponseEntity.ok(receptions);
    }

    @Override
    public ResponseEntity<ReceptionResponseDTO> updateReceptionConformite(Long id, Boolean conformite) {
        ReceptionResponseDTO updatedReception = receptionService.updateReceptionConformite(id, conformite);
        return ResponseEntity.ok(updatedReception);
    }
}

