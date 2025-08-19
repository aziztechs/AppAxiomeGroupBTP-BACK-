package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

import java.util.Set;

@Data
public class UtilisateurResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String statut;
    private Set<String> roles;

}
