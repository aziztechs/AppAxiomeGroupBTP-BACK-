package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.EcritureComptableController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.EcritureComptableRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.EcritureComptableResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.EcritureComptableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EcritureComptableControllerImpl implements EcritureComptableController {

    private final EcritureComptableService ecritureComptableService;

    @Override
    public ResponseEntity<EcritureComptableResponseDTO> createEcritureComptable(EcritureComptableRequestDTO ecritureDTO) {
        EcritureComptableResponseDTO createdEcriture = ecritureComptableService.createEcritureComptable(ecritureDTO);
        return new ResponseEntity<>(createdEcriture, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EcritureComptableResponseDTO> updateEcritureComptable(Long id, EcritureComptableRequestDTO ecritureDTO) {
        EcritureComptableResponseDTO updatedEcriture = ecritureComptableService.updateEcritureComptable(id, ecritureDTO);
        return ResponseEntity.ok(updatedEcriture);
    }

    @Override
    public ResponseEntity<Void> deleteEcritureComptable(Long id) {
        ecritureComptableService.deleteEcritureComptable(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<EcritureComptableResponseDTO> getEcritureComptableById(Long id) {
        EcritureComptableResponseDTO ecriture = ecritureComptableService.getEcritureComptableById(id);
        return ResponseEntity.ok(ecriture);
    }

    @Override
    public ResponseEntity<List<EcritureComptableResponseDTO>> getEcrituresByFacture(Long factureId) {
        List<EcritureComptableResponseDTO> ecritures = ecritureComptableService.getEcrituresByFacture(factureId);
        return ResponseEntity.ok(ecritures);
    }

    @Override
    public ResponseEntity<List<EcritureComptableResponseDTO>> getEcrituresByType(String type) {
        List<EcritureComptableResponseDTO> ecritures = ecritureComptableService.getEcrituresByType(type);
        return ResponseEntity.ok(ecritures);
    }

    @Override
    public ResponseEntity<Double> getTotalByType(String type) {
        Double total = ecritureComptableService.getTotalByType(type);
        return ResponseEntity.ok(total);
    }
}

