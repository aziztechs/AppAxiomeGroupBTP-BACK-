package aziztechs.sn.appaxiomegroupbtpback.dto;



import java.time.Duration;
import java.util.Map;

public class PerformanceReportDTO {
    private Duration tempsMoyenResolutionIncidents;
    private double tauxSatisfactionClient;
    private Map<String, Double> productiviteParEmploye;
    private Map<String, Integer> chantiersParResponsable;
    private double tauxRentabiliteMoyen;
    private double tauxCompletionMoyen;

    // Constructeurs
    public PerformanceReportDTO() {}

    public PerformanceReportDTO(Duration tempsMoyenResolutionIncidents,
                                double tauxSatisfactionClient,
                                Map<String, Double> productiviteParEmploye,
                                Map<String, Integer> chantiersParResponsable,
                                double tauxRentabiliteMoyen,
                                double tauxCompletionMoyen) {
        this.tempsMoyenResolutionIncidents = tempsMoyenResolutionIncidents;
        this.tauxSatisfactionClient = tauxSatisfactionClient;
        this.productiviteParEmploye = productiviteParEmploye;
        this.chantiersParResponsable = chantiersParResponsable;
        this.tauxRentabiliteMoyen = tauxRentabiliteMoyen;
        this.tauxCompletionMoyen = tauxCompletionMoyen;
    }

    // Getters et Setters
    public Duration getTempsMoyenResolutionIncidents() {
        return tempsMoyenResolutionIncidents;
    }

    public void setTempsMoyenResolutionIncidents(Duration tempsMoyenResolutionIncidents) {
        this.tempsMoyenResolutionIncidents = tempsMoyenResolutionIncidents;
    }

    public double getTauxSatisfactionClient() {
        return tauxSatisfactionClient;
    }

    public void setTauxSatisfactionClient(double tauxSatisfactionClient) {
        this.tauxSatisfactionClient = tauxSatisfactionClient;
    }

    public Map<String, Double> getProductiviteParEmploye() {
        return productiviteParEmploye;
    }

    public void setProductiviteParEmploye(Map<String, Double> productiviteParEmploye) {
        this.productiviteParEmploye = productiviteParEmploye;
    }

    public Map<String, Integer> getChantiersParResponsable() {
        return chantiersParResponsable;
    }

    public void setChantiersParResponsable(Map<String, Integer> chantiersParResponsable) {
        this.chantiersParResponsable = chantiersParResponsable;
    }

    public double getTauxRentabiliteMoyen() {
        return tauxRentabiliteMoyen;
    }

    public void setTauxRentabiliteMoyen(double tauxRentabiliteMoyen) {
        this.tauxRentabiliteMoyen = tauxRentabiliteMoyen;
    }

    public double getTauxCompletionMoyen() {
        return tauxCompletionMoyen;
    }

    public void setTauxCompletionMoyen(double tauxCompletionMoyen) {
        this.tauxCompletionMoyen = tauxCompletionMoyen;
    }

    // Méthodes utilitaires
    public String getTempsMoyenResolutionFormatted() {
        if (tempsMoyenResolutionIncidents == null) return "N/A";

        long seconds = tempsMoyenResolutionIncidents.getSeconds();
        long absSeconds = Math.abs(seconds);
        return String.format(
                "%d jours %02d:%02d:%02d",
                absSeconds / 86400,
                (absSeconds % 86400) / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
    }

    public String getTauxSatisfactionClientFormatted() {
        return String.format("%.1f%%", tauxSatisfactionClient * 100);
    }

    public String getTauxRentabiliteMoyenFormatted() {
        return String.format("%.1f%%", tauxRentabiliteMoyen * 100);
    }

    public String getTauxCompletionMoyenFormatted() {
        return String.format("%.1f%%", tauxCompletionMoyen * 100);
    }
}
