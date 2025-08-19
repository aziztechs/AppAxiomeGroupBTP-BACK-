package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.FactureController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FactureRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FactureResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.FactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FactureControllerImpl implements FactureController {

    private final FactureService factureService;

    @Override
    public ResponseEntity<FactureResponseDTO> createFacture(FactureRequestDTO factureDTO) {
        FactureResponseDTO createdFacture = factureService.createFacture(factureDTO);
        return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<FactureResponseDTO> updateFacture(Long id, FactureRequestDTO factureDTO) {
        FactureResponseDTO updatedFacture = factureService.updateFacture(id, factureDTO);
        return ResponseEntity.ok(updatedFacture);
    }

    @Override
    public ResponseEntity<Void> deleteFacture(Long id) {
        factureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<FactureResponseDTO> getFactureById(Long id) {
        FactureResponseDTO facture = factureService.getFactureById(id);
        return ResponseEntity.ok(facture);
    }

    @Override
    public ResponseEntity<List<FactureResponseDTO>> getAllFactures() {
        List<FactureResponseDTO> factures = factureService.getAllFactures();
        return ResponseEntity.ok(factures);
    }

    @Override
    public ResponseEntity<List<FactureResponseDTO>> getFacturesByStatut(String statut) {
        List<FactureResponseDTO> factures = factureService.getFacturesByStatut(statut);
        return ResponseEntity.ok(factures);
    }

    @Override
    public ResponseEntity<FactureResponseDTO> updateFactureStatut(Long id, String statut) {
        FactureResponseDTO updatedFacture = factureService.updateFactureStatut(id, statut);
        return ResponseEntity.ok(updatedFacture);
    }

    @Override
    public ResponseEntity<Double> getTotalFacturesPayees() {
        Double total = factureService.getTotalFacturesPayees();
        return ResponseEntity.ok(total);
    }
}

