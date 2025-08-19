package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FactureRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FactureResponseDTO;

import java.util.List;

public interface FactureService {
    FactureResponseDTO createFacture(FactureRequestDTO factureDTO);
    FactureResponseDTO updateFacture(Long id, FactureRequestDTO factureDTO);
    void deleteFacture(Long id);
    FactureResponseDTO getFactureById(Long id);
    List<FactureResponseDTO> getAllFactures();
    List<FactureResponseDTO> getFacturesByStatut(String statut);
    FactureResponseDTO updateFactureStatut(Long id, String statut);
    Double getTotalFacturesPayees();
}
