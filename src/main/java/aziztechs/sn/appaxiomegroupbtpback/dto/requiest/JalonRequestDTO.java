package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
@Data
public class JalonRequestDTO {
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @FutureOrPresent(message = "La date de début doit être dans le présent ou le futur")
    private LocalDate dateDebut;

    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;

    private String statut;

    @NotNull(message = "L'ID du chantier est obligatoire")
    private Long chantierId;

}
