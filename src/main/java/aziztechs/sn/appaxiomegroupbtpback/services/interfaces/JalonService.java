package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.JalonRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.JalonResponseDTO;

import java.util.List;

public interface JalonService {
    JalonResponseDTO createJalon(JalonRequestDTO jalonDTO);
    JalonResponseDTO updateJalon(Long id, JalonRequestDTO jalonDTO);
    void deleteJalon(Long id);
    JalonResponseDTO getJalonById(Long id);
    List<JalonResponseDTO> getJalonsByChantier(Long chantierId);
    List<JalonResponseDTO> getJalonsByStatut(String statut);
    JalonResponseDTO updateJalonStatut(Long id, String statut);
}
