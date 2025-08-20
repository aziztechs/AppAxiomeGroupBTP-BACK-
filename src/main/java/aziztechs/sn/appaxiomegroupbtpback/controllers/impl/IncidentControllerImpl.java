package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.IncidentController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.IncidentRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.IncidentResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IncidentControllerImpl implements IncidentController {

    private final IncidentService incidentService;

    @Override
    public ResponseEntity<IncidentResponseDTO> createIncident(IncidentRequestDTO incidentDTO) {
        IncidentResponseDTO createdIncident = incidentService.createIncident(incidentDTO);
        return new ResponseEntity<>(createdIncident, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<IncidentResponseDTO> updateIncident(Long id, IncidentRequestDTO incidentDTO) {
        IncidentResponseDTO updatedIncident = incidentService.updateIncident(id, incidentDTO);
        return ResponseEntity.ok(updatedIncident);
    }

    @Override
    public ResponseEntity<Void> deleteIncident(Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<IncidentResponseDTO> getIncidentById(Long id) {
        IncidentResponseDTO incident = incidentService.getIncidentById(id);
        return ResponseEntity.ok(incident);
    }

    @Override
    public ResponseEntity<List<IncidentResponseDTO>> getIncidentsByChantier(Long chantierId) {
        List<IncidentResponseDTO> incidents = incidentService.getIncidentsByChantier(chantierId);
        return ResponseEntity.ok(incidents);
    }

    @Override
    public ResponseEntity<List<IncidentResponseDTO>> getIncidentsByTypeAndStatut(String type, String statut) {
        List<IncidentResponseDTO> incidents = incidentService.getIncidentsByTypeAndStatut(type, statut);
        return ResponseEntity.ok(incidents);
    }

    @Override
    public ResponseEntity<IncidentResponseDTO> updateIncidentStatut(Long id, String statut) {
        IncidentResponseDTO updatedIncident = incidentService.updateIncidentStatut(id, statut);
        return ResponseEntity.ok(updatedIncident);
    }
}

