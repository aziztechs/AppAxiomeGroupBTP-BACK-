package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FactureResponseDTO {
    private Long id;
    private String numero;
    private LocalDate date;
    private double montant;
    private String statut;
    private List<EcritureComptableResponseDTO> ecrituresComptables;

}
