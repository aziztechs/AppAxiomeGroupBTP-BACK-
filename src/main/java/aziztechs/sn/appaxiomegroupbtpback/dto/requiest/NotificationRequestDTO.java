package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class NotificationRequestDTO {
    @NotBlank(message = "Le message est obligatoire")
    private String message;

    @NotNull(message = "L'ID de l'utilisateur est obligatoire")
    private Long utilisateurId;

}
