package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ChantierResponseDTO {
    private Long id;
    private String reference;
    private String client;
    private String localisation;
    private double budgetInitial;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;

    // Getters and Setters
}
