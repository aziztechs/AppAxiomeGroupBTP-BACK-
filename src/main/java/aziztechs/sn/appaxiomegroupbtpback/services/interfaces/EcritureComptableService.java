package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.EcritureComptableRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.EcritureComptableResponseDTO;

import java.util.List;

public interface EcritureComptableService {
    EcritureComptableResponseDTO createEcritureComptable(EcritureComptableRequestDTO ecritureDTO);
    EcritureComptableResponseDTO updateEcritureComptable(Long id, EcritureComptableRequestDTO ecritureDTO);
    void deleteEcritureComptable(Long id);
    EcritureComptableResponseDTO getEcritureComptableById(Long id);
    List<EcritureComptableResponseDTO> getEcrituresByFacture(Long factureId);
    List<EcritureComptableResponseDTO> getEcrituresByType(String type);
    Double getTotalByType(String type);
}
