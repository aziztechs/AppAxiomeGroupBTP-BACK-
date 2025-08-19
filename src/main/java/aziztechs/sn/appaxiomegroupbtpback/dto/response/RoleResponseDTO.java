package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.util.Set;

@Data
public class RoleResponseDTO {
    private Long id;
    private String nom;
    private Set<String> permissions;

}