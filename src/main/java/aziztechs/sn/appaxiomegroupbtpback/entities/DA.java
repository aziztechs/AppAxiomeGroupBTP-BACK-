package aziztechs.sn.appaxiomegroupbtpback.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "das")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reference;

    private LocalDate dateEmission;

    private String statut;

    private double montant;

}
