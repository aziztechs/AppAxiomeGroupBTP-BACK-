package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DaResponseDTO {
    private Long id;
    private String reference;
    private LocalDate dateEmission;
    private String statut;
    private double montant;

    // Getters and Setters
}
