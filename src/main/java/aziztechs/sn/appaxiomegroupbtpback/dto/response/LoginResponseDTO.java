package aziztechs.sn.appaxiomegroupbtpback.dto.response;


import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private UtilisateurResponseDTO utilisateur;

    public LoginResponseDTO(String jwt, UtilisateurResponseDTO currentUserProfile) {
        this.token = jwt;
        this.utilisateur = currentUserProfile;
    }

}
