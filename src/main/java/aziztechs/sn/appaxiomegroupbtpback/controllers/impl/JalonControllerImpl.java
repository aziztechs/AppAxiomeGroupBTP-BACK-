package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.JalonController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.JalonRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.JalonResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.JalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JalonControllerImpl implements JalonController {

    private final JalonService jalonService;

    @Override
    public ResponseEntity<JalonResponseDTO> createJalon(JalonRequestDTO jalonDTO) {
        JalonResponseDTO createdJalon = jalonService.createJalon(jalonDTO);
        return new ResponseEntity<>(createdJalon, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<JalonResponseDTO> updateJalon(Long id, JalonRequestDTO jalonDTO) {
        JalonResponseDTO updatedJalon = jalonService.updateJalon(id, jalonDTO);
        return ResponseEntity.ok(updatedJalon);
    }

    @Override
    public ResponseEntity<Void> deleteJalon(Long id) {
        jalonService.deleteJalon(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<JalonResponseDTO> getJalonById(Long id) {
        JalonResponseDTO jalon = jalonService.getJalonById(id);
        return ResponseEntity.ok(jalon);
    }

    @Override
    public ResponseEntity<List<JalonResponseDTO>> getJalonsByChantier(Long chantierId) {
        List<JalonResponseDTO> jalons = jalonService.getJalonsByChantier(chantierId);
        return ResponseEntity.ok(jalons);
    }

    @Override
    public ResponseEntity<List<JalonResponseDTO>> getJalonsByStatut(String statut) {
        List<JalonResponseDTO> jalons = jalonService.getJalonsByStatut(statut);
        return ResponseEntity.ok(jalons);
    }

    @Override
    public ResponseEntity<JalonResponseDTO> updateJalonStatut(Long id, String statut) {
        JalonResponseDTO updatedJalon = jalonService.updateJalonStatut(id, statut);
        return ResponseEntity.ok(updatedJalon);
    }
}

