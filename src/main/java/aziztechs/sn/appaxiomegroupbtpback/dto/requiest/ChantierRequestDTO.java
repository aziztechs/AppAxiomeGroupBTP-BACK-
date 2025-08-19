package aziztechs.sn.appaxiomegroupbtpback.dto.requiest;


import lombok.Data;


import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ChantierRequestDTO {
    @NotBlank(message = "La référence est obligatoire")
    private String reference;

    @NotBlank(message = "Le client est obligatoire")
    private String client;

    @NotBlank(message = "La localisation est obligatoire")
    private String localisation;

    @PositiveOrZero(message = "Le budget doit être positif ou zéro")
    private double budgetInitial;

    @FutureOrPresent(message = "La date de début doit être dans le présent ou le futur")
    private LocalDate dateDebut;

    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;

}
