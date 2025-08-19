package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class FactureRequestDTO {
    @NotBlank(message = "Le numéro est obligatoire")
    private String numero;

    private LocalDate date;

    @Positive(message = "Le montant doit être positif")
    private double montant;

    private String statut;

}
