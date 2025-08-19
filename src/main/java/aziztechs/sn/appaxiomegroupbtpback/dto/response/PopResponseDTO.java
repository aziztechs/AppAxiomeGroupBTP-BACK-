package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDate;
@Data
public class PopResponseDTO {
    private Long id;
    private String reference;
    private LocalDate date;
    private String statut;
    private double montant;

}
