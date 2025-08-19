package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;


import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.ChantierController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.ChantierRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ChantierDetailResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ChantierResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.ChantierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChantierControllerImpl implements ChantierController {

    private final ChantierService chantierService;

    public ChantierControllerImpl(ChantierService chantierService) {
        this.chantierService = chantierService;
    }

    @Override
    public ResponseEntity<ChantierResponseDTO> createChantier(@RequestBody ChantierRequestDTO chantierDTO) {
        ChantierResponseDTO response = chantierService.createChantier(chantierDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ChantierResponseDTO> updateChantier(@PathVariable Long id,
                                                              @RequestBody ChantierRequestDTO chantierDTO) {
        ChantierResponseDTO response = chantierService.updateChantier(id, chantierDTO);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteChantier(@PathVariable Long id) {
        chantierService.deleteChantier(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ChantierResponseDTO> getChantierById(@PathVariable Long id) {
        ChantierResponseDTO response = chantierService.getChantierById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ChantierDetailResponseDTO> getChantierDetails(@PathVariable Long id) {
        ChantierDetailResponseDTO response = chantierService.getChantierDetailsById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ChantierResponseDTO>> getAllChantiers() {
        List<ChantierResponseDTO> response = chantierService.getAllChantiers();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ChantierResponseDTO>> getChantiersByStatut(@PathVariable String statut) {
        List<ChantierResponseDTO> response = chantierService.getChantiersByStatut(statut);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ChantierResponseDTO>> getChantiersEnRetard() {
        List<ChantierResponseDTO> response = chantierService.getChantiersEnRetard();
        return ResponseEntity.ok(response);
    }
}
