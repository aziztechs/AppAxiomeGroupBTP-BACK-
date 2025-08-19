package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReceptionRequestDTO {
    private LocalDate date;

    @Positive(message = "La quantité doit être positive")
    private Integer quantite;

    private Boolean conformite;

}
