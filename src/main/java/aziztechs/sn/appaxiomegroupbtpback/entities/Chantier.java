package aziztechs.sn.appaxiomegroupbtpback.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "chantiers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chantier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column(nullable = false)
    private String client;

    @Column(nullable = false)
    private String localisation;

    private double budgetInitial;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    @OneToMany(mappedBy = "chantier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Jalon> jalons;

    @OneToMany(mappedBy = "chantier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Document> documents;

    @OneToMany(mappedBy = "chantier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Incident> incidents;

    public void setStatut(String planifie) {
    }

    public void setBudget(Budget budget) {
    }
}
