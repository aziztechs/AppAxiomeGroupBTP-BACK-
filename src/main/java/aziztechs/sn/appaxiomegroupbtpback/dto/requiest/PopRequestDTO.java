package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PopRequestDTO {
    @NotBlank(message = "La référence est obligatoire")
    private String reference;

    private LocalDate date;

    private String statut;

    @Positive(message = "Le montant doit être positif")
    private double montant;

}
