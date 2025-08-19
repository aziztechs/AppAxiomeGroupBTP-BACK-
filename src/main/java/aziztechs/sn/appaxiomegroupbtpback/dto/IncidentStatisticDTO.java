package aziztechs.sn.appaxiomegroupbtpback.dto;


import java.time.LocalDate;

public class IncidentStatisticDTO {
    private Long incidentId;
    private String type;
    private String description;
    private LocalDate date;
    private String statut;
    private Long chantierId;
    private String chantierReference;
    private int joursResolution;
    private String gravite;

    // Constructeurs
    public IncidentStatisticDTO() {}

    public IncidentStatisticDTO(Long incidentId, String type, String description, LocalDate date,
                                String statut, Long chantierId, String chantierReference,
                                LocalDate dateResolution) {
        this.incidentId = incidentId;
        this.type = type;
        this.description = description;
        this.date = date;
        this.statut = statut;
        this.chantierId = chantierId;
        this.chantierReference = chantierReference;

        // Calcul des jours de résolution si l'incident est résolu
        if (dateResolution != null && "RESOLU".equals(statut)) {
            this.joursResolution = (int) java.time.temporal.ChronoUnit.DAYS.between(date, dateResolution);
        } else {
            this.joursResolution = -1; // Non résolu
        }

        // Détermination de la gravité
        this.gravite = determineGravite(type, joursResolution);
    }

    private String determineGravite(String type, int joursResolution) {
        if (joursResolution < 0) return "CRITIQUE"; // Non résolu
        if (joursResolution > 7) return "HAUTE";
        if (joursResolution > 3) return "MOYENNE";
        return "FAIBLE";
    }

    // Getters et Setters
    public Long getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getChantierId() {
        return chantierId;
    }

    public void setChantierId(Long chantierId) {
        this.chantierId = chantierId;
    }

    public String getChantierReference() {
        return chantierReference;
    }

    public void setChantierReference(String chantierReference) {
        this.chantierReference = chantierReference;
    }

    public int getJoursResolution() {
        return joursResolution;
    }

    public void setJoursResolution(int joursResolution) {
        this.joursResolution = joursResolution;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }
}
