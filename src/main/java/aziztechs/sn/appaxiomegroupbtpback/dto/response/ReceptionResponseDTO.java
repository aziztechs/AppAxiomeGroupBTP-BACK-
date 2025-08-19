package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ReceptionResponseDTO {
    private Long id;
    private LocalDate date;
    private Integer quantite;
    private Boolean conformite;

}
