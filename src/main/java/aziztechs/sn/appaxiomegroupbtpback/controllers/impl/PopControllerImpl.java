package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.PopController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.PopRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.PopResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.PopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PopControllerImpl implements PopController {

    private final PopService popService;

    @Override
    public ResponseEntity<PopResponseDTO> createPop(PopRequestDTO popDTO) {
        PopResponseDTO createdPop = popService.createPop(popDTO);
        return new ResponseEntity<>(createdPop, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PopResponseDTO> updatePop(Long id, PopRequestDTO popDTO) {
        PopResponseDTO updatedPop = popService.updatePop(id, popDTO);
        return ResponseEntity.ok(updatedPop);
    }

    @Override
    public ResponseEntity<Void> deletePop(Long id) {
        popService.deletePop(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PopResponseDTO> getPopById(Long id) {
        PopResponseDTO pop = popService.getPopById(id);
        return ResponseEntity.ok(pop);
    }

    @Override
    public ResponseEntity<List<PopResponseDTO>> getAllPops() {
        List<PopResponseDTO> pops = popService.getAllPops();
        return ResponseEntity.ok(pops);
    }

    @Override
    public ResponseEntity<List<PopResponseDTO>> getPopsByStatut(String statut) {
        List<PopResponseDTO> pops = popService.getPopsByStatut(statut);
        return ResponseEntity.ok(pops);
    }

    @Override
    public ResponseEntity<PopResponseDTO> updatePopStatut(Long id, String statut) {
        PopResponseDTO updatedPop = popService.updatePopStatut(id, statut);
        return ResponseEntity.ok(updatedPop);
    }
}

