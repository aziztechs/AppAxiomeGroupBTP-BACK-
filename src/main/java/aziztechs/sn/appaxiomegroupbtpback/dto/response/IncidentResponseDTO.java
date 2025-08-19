package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class IncidentResponseDTO {
    private Long id;
    private String type;
    private String description;
    private LocalDate date;
    private String statut;
    private Long chantierId;
    private String chantierReference;

}
