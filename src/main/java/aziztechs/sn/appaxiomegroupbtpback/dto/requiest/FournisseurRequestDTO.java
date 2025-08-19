package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FournisseurRequestDTO {
    @NotBlank(message = "La raison sociale est obligatoire")
    private String raisonSociale;

    private String adresse;

    private String contact;

    @Min(value = 0, message = "La notation doit être entre 0 et 5")
    @Max(value = 5, message = "La notation doit être entre 0 et 5")
    private Integer notation;

    // Getters and Setters
}
