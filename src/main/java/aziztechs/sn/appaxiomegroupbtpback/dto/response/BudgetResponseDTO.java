package aziztechs.sn.appaxiomegroupbtpback.dto.response;

import lombok.Data;

@Data
public class BudgetResponseDTO {
    private Long id;
    private double montantPrevu;
    private double montantEngage;
    private double montantRealise;
    private double tauxEngagement;
    private double tauxRealisation;

}