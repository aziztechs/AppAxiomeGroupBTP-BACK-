package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncidentRequestDTO {
    @NotBlank(message = "Le type est obligatoire")
    private String type;

    private String description;

    private LocalDate date;

    private String statut;

    private Long chantierId;

}