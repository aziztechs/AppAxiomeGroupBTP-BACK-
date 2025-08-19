package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.IncidentRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.IncidentResponseDTO;

import java.util.List;

public interface IncidentService {

    IncidentResponseDTO createIncident(IncidentRequestDTO incidentDTO);
    IncidentResponseDTO updateIncident(Long id, IncidentRequestDTO incidentDTO);
    void deleteIncident(Long id);
    IncidentResponseDTO getIncidentById(Long id);
    List<IncidentResponseDTO> getIncidentsByChantier(Long chantierId);
    List<IncidentResponseDTO> getIncidentsByTypeAndStatut(String type, String statut);
    IncidentResponseDTO updateIncidentStatut(Long id, String statut);
}
