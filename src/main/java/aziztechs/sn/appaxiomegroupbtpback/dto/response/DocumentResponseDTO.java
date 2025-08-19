package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DocumentResponseDTO {
    private Long id;
    private String type;
    private String cheminFichier;
    private String nomFichier;
    private LocalDateTime dateUpload;
    private Long chantierId;
    private String chantierReference;

}
