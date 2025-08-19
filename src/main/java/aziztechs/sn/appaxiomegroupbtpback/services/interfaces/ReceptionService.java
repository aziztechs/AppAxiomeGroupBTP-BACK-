package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.ReceptionRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ReceptionResponseDTO;

public interface ReceptionService {
    ReceptionResponseDTO createReception(ReceptionRequestDTO receptionDTO);
    ReceptionResponseDTO updateReception(Long id, ReceptionRequestDTO receptionDTO);
    ReceptionResponseDTO getReceptionById(Long id);
    List<ReceptionResponseDTO> getAllReceptions();
    List<ReceptionResponseDTO> getReceptionsByConformite(Boolean conformite);
    ReceptionResponseDTO updateReceptionConformite(Long id, Boolean conformite);
}
