package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class EcritureComptableResponseDTO {
    private Long id;
    private String type;
    private double montant;
    private LocalDate date;
    private String compte;
    private Long factureId;
    private String factureNumero;

}
