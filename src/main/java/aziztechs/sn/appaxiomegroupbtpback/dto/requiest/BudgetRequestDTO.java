package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BudgetRequestDTO {
    @PositiveOrZero(message = "Le montant prévu doit être positif ou zéro")
    private double montantPrevu;

    @PositiveOrZero(message = "Le montant engagé doit être positif ou zéro")
    private double montantEngage;

    @PositiveOrZero(message = "Le montant réalisé doit être positif ou zéro")
    private double montantRealise;

}
