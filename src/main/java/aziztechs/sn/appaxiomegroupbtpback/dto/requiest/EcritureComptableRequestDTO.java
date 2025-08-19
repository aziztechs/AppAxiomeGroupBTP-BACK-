package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EcritureComptableRequestDTO {
    @NotBlank(message = "Le type est obligatoire")
    private String type;

    @Positive(message = "Le montant doit être positif")
    private double montant;

    private LocalDate date;

    private String compte;

    private Long factureId;

}
