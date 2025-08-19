package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.DaRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.DaResponseDTO;

import java.util.List;

public interface DaService {
    DaResponseDTO createDa(DaRequestDTO daDTO);
    DaResponseDTO updateDa(Long id, DaRequestDTO daDTO);
    void deleteDa(Long id);
    DaResponseDTO getDaById(Long id);
    List<DaResponseDTO> getAllDas();
    List<DaResponseDTO> getDasByStatut(String statut);
    DaResponseDTO updateDaStatut(Long id, String statut);
}
