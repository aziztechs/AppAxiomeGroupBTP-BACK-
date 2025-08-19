package aziztechs.sn.appaxiomegroupbtpback.dto;


import java.util.Map;

public class DashboardSummaryDTO {
    private int nombreChantiersActifs;
    private int nombreChantiersTermines;
    private int nombreIncidentsNonResolus;
    private double tauxCompletionGlobal;
    private Map<String, Integer> chantiersParStatut;
    private Map<String, Integer> incidentsParType;
    private FinancialSnapshotDTO snapshotFinancier;
    private AlertDTO alertes;

    // Constructeurs
    public DashboardSummaryDTO() {}

    public DashboardSummaryDTO(int nombreChantiersActifs, int nombreChantiersTermines,
                               int nombreIncidentsNonResolus, double tauxCompletionGlobal,
                               Map<String, Integer> chantiersParStatut,
                               Map<String, Integer> incidentsParType,
                               FinancialSnapshotDTO snapshotFinancier,
                               AlertDTO alertes) {
        this.nombreChantiersActifs = nombreChantiersActifs;
        this.nombreChantiersTermines = nombreChantiersTermines;
        this.nombreIncidentsNonResolus = nombreIncidentsNonResolus;
        this.tauxCompletionGlobal = tauxCompletionGlobal;
        this.chantiersParStatut = chantiersParStatut;
        this.incidentsParType = incidentsParType;
        this.snapshotFinancier = snapshotFinancier;
        this.alertes = alertes;
    }

    // Getters et Setters
    public int getNombreChantiersActifs() {
        return nombreChantiersActifs;
    }

    public void setNombreChantiersActifs(int nombreChantiersActifs) {
        this.nombreChantiersActifs = nombreChantiersActifs;
    }

    public int getNombreChantiersTermines() {
        return nombreChantiersTermines;
    }

    public void setNombreChantiersTermines(int nombreChantiersTermines) {
        this.nombreChantiersTermines = nombreChantiersTermines;
    }

    public int getNombreIncidentsNonResolus() {
        return nombreIncidentsNonResolus;
    }

    public void setNombreIncidentsNonResolus(int nombreIncidentsNonResolus) {
        this.nombreIncidentsNonResolus = nombreIncidentsNonResolus;
    }

    public double getTauxCompletionGlobal() {
        return tauxCompletionGlobal;
    }

    public void setTauxCompletionGlobal(double tauxCompletionGlobal) {
        this.tauxCompletionGlobal = tauxCompletionGlobal;
    }

    public Map<String, Integer> getChantiersParStatut() {
        return chantiersParStatut;
    }

    public void setChantiersParStatut(Map<String, Integer> chantiersParStatut) {
        this.chantiersParStatut = chantiersParStatut;
    }

    public Map<String, Integer> getIncidentsParType() {
        return incidentsParType;
    }

    public void setIncidentsParType(Map<String, Integer> incidentsParType) {
        this.incidentsParType = incidentsParType;
    }

    public FinancialSnapshotDTO getSnapshotFinancier() {
        return snapshotFinancier;
    }

    public void setSnapshotFinancier(FinancialSnapshotDTO snapshotFinancier) {
        this.snapshotFinancier = snapshotFinancier;
    }

    public AlertDTO getAlertes() {
        return alertes;
    }

    public void setAlertes(AlertDTO alertes) {
        this.alertes = alertes;
    }
}

// Classes internes pour DashboardSummaryDTO
public class FinancialSnapshotDTO {
    private double budgetTotal;
    private double depensesTotal;
    private double revenusTotal;
    private double beneficeNet;
    private double tauxDepenses;

    public FinancialSnapshotDTO(double budgetTotal, double depensesTotal, double revenusTotal) {
        this.budgetTotal = budgetTotal;
        this.depensesTotal = depensesTotal;
        this.revenusTotal = revenusTotal;
        this.beneficeNet = revenusTotal - depensesTotal;
        this.tauxDepenses = (budgetTotal > 0) ? (depensesTotal / budgetTotal) * 100 : 0;
    }

    // Getters
    public double getBudgetTotal() {
        return budgetTotal;
    }

    public double getDepensesTotal() {
        return depensesTotal;
    }

    public double getRevenusTotal() {
        return revenusTotal;
    }

    public double getBeneficeNet() {
        return beneficeNet;
    }

    public double getTauxDepenses() {
        return tauxDepenses;
    }
}

public class AlertDTO {
    private int chantiersEnRetard;
    private int budgetsDepasses;
    private int incidentsCritiques;
    private int facturesImpayees;

    public AlertDTO(int chantiersEnRetard, int budgetsDepasses,
                    int incidentsCritiques, int facturesImpayees) {
        this.chantiersEnRetard = chantiersEnRetard;
        this.budgetsDepasses = budgetsDepasses;
        this.incidentsCritiques = incidentsCritiques;
        this.facturesImpayees = facturesImpayees;
    }

    // Getters
    public int getChantiersEnRetard() {
        return chantiersEnRetard;
    }

    public int getBudgetsDepasses() {
        return budgetsDepasses;
    }

    public int getIncidentsCritiques() {
        return incidentsCritiques;
    }

    public int getFacturesImpayees() {
        return facturesImpayees;
    }
}
