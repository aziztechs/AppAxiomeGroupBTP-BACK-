package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.FournisseurController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.FournisseurRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.FournisseurResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.FournisseurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FournisseurControllerImpl implements FournisseurController {

    private final FournisseurService fournisseurService;

    @Override
    public ResponseEntity<FournisseurResponseDTO> createFournisseur(FournisseurRequestDTO fournisseurDTO) {
        FournisseurResponseDTO createdFournisseur = fournisseurService.createFournisseur(fournisseurDTO);
        return new ResponseEntity<>(createdFournisseur, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<FournisseurResponseDTO> updateFournisseur(Long id, FournisseurRequestDTO fournisseurDTO) {
        FournisseurResponseDTO updatedFournisseur = fournisseurService.updateFournisseur(id, fournisseurDTO);
        return ResponseEntity.ok(updatedFournisseur);
    }

    @Override
    public ResponseEntity<Void> deleteFournisseur(Long id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<FournisseurResponseDTO> getFournisseurById(Long id) {
        FournisseurResponseDTO fournisseur = fournisseurService.getFournisseurById(id);
        return ResponseEntity.ok(fournisseur);
    }

    @Override
    public ResponseEntity<List<FournisseurResponseDTO>> getAllFournisseurs() {
        List<FournisseurResponseDTO> fournisseurs = fournisseurService.getAllFournisseurs();
        return ResponseEntity.ok(fournisseurs);
    }

    @Override
    public ResponseEntity<List<FournisseurResponseDTO>> searchFournisseurs(String keyword) {
        List<FournisseurResponseDTO> fournisseurs = fournisseurService.searchFournisseurs(keyword);
        return ResponseEntity.ok(fournisseurs);
    }

    @Override
    public ResponseEntity<FournisseurResponseDTO> updateFournisseurNotation(Long id, Integer notation) {
        FournisseurResponseDTO updatedFournisseur = fournisseurService.updateFournisseurNotation(id, notation);
        return ResponseEntity.ok(updatedFournisseur);
    }
}

