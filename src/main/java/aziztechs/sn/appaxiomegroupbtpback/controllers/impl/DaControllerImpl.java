package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.DaController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.DaRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.DaResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.DaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DaControllerImpl implements DaController {

    private final DaService daService;

    @Override
    public ResponseEntity<DaResponseDTO> createDa(DaRequestDTO daDTO) {
        DaResponseDTO createdDa = daService.createDa(daDTO);
        return new ResponseEntity<>(createdDa, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DaResponseDTO> updateDa(Long id, DaRequestDTO daDTO) {
        DaResponseDTO updatedDa = daService.updateDa(id, daDTO);
        return ResponseEntity.ok(updatedDa);
    }

    @Override
    public ResponseEntity<Void> deleteDa(Long id) {
        daService.deleteDa(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<DaResponseDTO> getDaById(Long id) {
        DaResponseDTO da = daService.getDaById(id);
        return ResponseEntity.ok(da);
    }

    @Override
    public ResponseEntity<List<DaResponseDTO>> getAllDas() {
        List<DaResponseDTO> das = daService.getAllDas();
        return ResponseEntity.ok(das);
    }

    @Override
    public ResponseEntity<List<DaResponseDTO>> getDasByStatut(String statut) {
        List<DaResponseDTO> das = daService.getDasByStatut(statut);
        return ResponseEntity.ok(das);
    }

    @Override
    public ResponseEntity<DaResponseDTO> updateDaStatut(Long id, String statut) {
        DaResponseDTO updatedDa = daService.updateDaStatut(id, statut);
        return ResponseEntity.ok(updatedDa);
    }
}

