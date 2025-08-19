package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.ChantierRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ChantierDetailResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.ChantierResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChantierService {

    ChantierResponseDTO createChantier(ChantierRequestDTO chantierDTO);
    ChantierResponseDTO updateChantier(Long id, ChantierRequestDTO chantierDTO);

    @Transactional
    ChantierResponseDTO createChantier(ChantierRequestDTO chantierDTO);

    @Transactional
    ChantierResponseDTO createChantier(ChantierRequestDTO chantierDTO);

    void deleteChantier(Long id);
    ChantierResponseDTO getChantierById(Long id);
    ChantierDetailResponseDTO getChantierDetailsById(Long id);
    List<ChantierResponseDTO> getAllChantiers();
    List<ChantierResponseDTO> getChantiersByClient(String client);
    List<ChantierResponseDTO> getActiveChantiers();
    List<ChantierResponseDTO> getCompletedChantiers();

    List<ChantierResponseDTO> getChantiersByStatut(String statut);
}
