package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DocumentRequestDTO {
    @NotBlank(message = "Le type est obligatoire")
    private String type;

    private Long chantierId;

}
