package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequestDTO {
    @NotBlank(message = "Le nom du rôle est obligatoire")
    private String nom;

    private Set<String> permissions;

}
