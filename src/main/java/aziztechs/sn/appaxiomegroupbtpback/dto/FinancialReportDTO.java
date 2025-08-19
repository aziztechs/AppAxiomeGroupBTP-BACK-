package aziztechs.sn.appaxiomegroupbtpback.dto;


import java.util.List;
import java.util.Map;

public class FinancialReportDTO {
    private int annee;
    private double totalRevenus;
    private double totalDepenses;
    private double beneficeNet;
    private Map<String, Double> depensesParCategorie;
    private Map<String, Double> revenusParClient;
    private List<ProjectFinancialSummaryDTO> projetsFinanciers;
    private double tauxRentabilite;
    private double margeBrute;

    // Constructeurs
    public FinancialReportDTO() {}

    public FinancialReportDTO(int annee, double totalRevenus, double totalDepenses,
                              Map<String, Double> depensesParCategorie,
                              Map<String, Double> revenusParClient,
                              List<ProjectFinancialSummaryDTO> projetsFinanciers) {
        this.annee = annee;
        this.totalRevenus = totalRevenus;
        this.totalDepenses = totalDepenses;
        this.beneficeNet = totalRevenus - totalDepenses;
        this.depensesParCategorie = depensesParCategorie;
        this.revenusParClient = revenusParClient;
        this.projetsFinanciers = projetsFinanciers;
        this.tauxRentabilite = (totalRevenus > 0) ? (beneficeNet / totalRevenus) * 100 : 0;
        this.margeBrute = (totalRevenus > 0) ? ((totalRevenus - totalDepenses) / totalRevenus) * 100 : 0;
    }

    // Getters et Setters
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public double getTotalRevenus() {
        return totalRevenus;
    }

    public void setTotalRevenus(double totalRevenus) {
        this.totalRevenus = totalRevenus;
        this.beneficeNet = this.totalRevenus - this.totalDepenses;
        this.tauxRentabilite = (totalRevenus > 0) ? (this.beneficeNet / totalRevenus) * 100 : 0;
        this.margeBrute = (totalRevenus > 0) ? ((totalRevenus - this.totalDepenses) / totalRevenus) * 100 : 0;
    }

    public double getTotalDepenses() {
        return totalDepenses;
    }

    public void setTotalDepenses(double totalDepenses) {
        this.totalDepenses = totalDepenses;
        this.beneficeNet = this.totalRevenus - totalDepenses;
        this.tauxRentabilite = (this.totalRevenus > 0) ? (this.beneficeNet / this.totalRevenus) * 100 : 0;
        this.margeBrute = (this.totalRevenus > 0) ? ((this.totalRevenus - totalDepenses) / this.totalRevenus) * 100 : 0;
    }

    public double getBeneficeNet() {
        return beneficeNet;
    }

    public Map<String, Double> getDepensesParCategorie() {
        return depensesParCategorie;
    }

    public void setDepensesParCategorie(Map<String, Double> depensesParCategorie) {
        this.depensesParCategorie = depensesParCategorie;
    }

    public Map<String, Double> getRevenusParClient() {
        return revenusParClient;
    }

    public void setRevenusParClient(Map<String, Double> revenusParClient) {
        this.revenusParClient = revenusParClient;
    }

    public List<ProjectFinancialSummaryDTO> getProjetsFinanciers() {
        return projetsFinanciers;
    }

    public void setProjetsFinanciers(List<ProjectFinancialSummaryDTO> projetsFinanciers) {
        this.projetsFinanciers = projetsFinanciers;
    }

    public double getTauxRentabilite() {
        return tauxRentabilite;
    }

    public double getMargeBrute() {
        return margeBrute;
    }
}

// Classe supplémentaire pour les résumés financiers par projet
class ProjectFinancialSummaryDTO {
    private Long chantierId;
    private String reference;
    private String client;
    private double budget;
    private double depenses;
    private double revenus;
    private double profit;

    public ProjectFinancialSummaryDTO(Long chantierId, String reference, String client,
                                      double budget, double depenses, double revenus) {
        this.chantierId = chantierId;
        this.reference = reference;
        this.client = client;
        this.budget = budget;
        this.depenses = depenses;
        this.revenus = revenus;
        this.profit = revenus - depenses;
    }

    // Getters
    public Long getChantierId() {
        return chantierId;
    }

    public String getReference() {
        return reference;
    }

    public String getClient() {
        return client;
    }

    public double getBudget() {
        return budget;
    }

    public double getDepenses() {
        return depenses;
    }

    public double getRevenus() {
        return revenus;
    }

    public double getProfit() {
        return profit;
    }
}

