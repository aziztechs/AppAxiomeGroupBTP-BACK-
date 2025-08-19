package aziztechs.sn.appaxiomegroupbtpback.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChantierStatisticDTO {
    private Long chantierId;
    private String reference;
    private String client;
    private String localisation;
    private double budgetInitial;
    private double depenses;
    private double pourcentageDepenses;
    private int nombreJalons;
    private int nombreJalonsTermines;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String statut;

}
