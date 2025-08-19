package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponseDTO {
    private Long id;
    private String message;
    private LocalDateTime date;
    private boolean lu;
    private Long utilisateurId;
    private String utilisateurNomComplet;

}
