package aziztechs.sn.appaxiomegroupbtpback.dto.response;

import lombok.Data;

@Data
public class FournisseurResponseDTO {
    private Long id;
    private String raisonSociale;
    private String adresse;
    private String contact;
    private Integer notation;

}
