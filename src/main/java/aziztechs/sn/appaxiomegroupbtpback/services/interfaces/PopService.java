package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.PopRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.PopResponseDTO;

import java.util.List;

public interface PopService {
    PopResponseDTO createPop(PopRequestDTO popDTO);
    PopResponseDTO updatePop(Long id, PopRequestDTO popDTO);
    void deletePop(Long id);
    PopResponseDTO getPopById(Long id);
    List<PopResponseDTO> getAllPops();
    List<PopResponseDTO> getPopsByStatut(String statut);
    PopResponseDTO updatePopStatut(Long id, String statut);
}
