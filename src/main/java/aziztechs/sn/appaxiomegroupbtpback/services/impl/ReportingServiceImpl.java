package aziztechs.sn.appaxiomegroupbtpback.services.impl;


import aziztechs.sn.appaxiomegroupbtpback.dto.*;
import aziztechs.sn.appaxiomegroupbtpback.repositories.*;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.ReportingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements ReportingService {

    private final ChantierRepository chantierRepository;
    private final IncidentRepository incidentRepository;
    private final BudgetRepository budgetRepository;
    private final FactureRepository factureRepository;
    private final UtilisateurRepository utilisateurRepository;

    public ReportingServiceImpl(ChantierRepository chantierRepository,
                                IncidentRepository incidentRepository,
                                BudgetRepository budgetRepository,
                                FactureRepository factureRepository,
                                UtilisateurRepository utilisateurRepository) {
        this.chantierRepository = chantierRepository;
        this.incidentRepository = incidentRepository;
        this.budgetRepository = budgetRepository;
        this.factureRepository = factureRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public DashboardSummaryDTO getDashboardSummary() {
        DashboardSummaryDTO dashboard = new DashboardSummaryDTO();

        // Statistiques des chantiers
        dashboard.setNombreChantiersActifs(chantierRepository.countByStatut("EN_COURS"));
        dashboard.setNombreChantiersTermines(chantierRepository.countByStatut("TERMINE"));
        dashboard.setNombreChantiersEnRetard(chantierRepository.countByDateFinBeforeAndStatutNot(LocalDate.now(), "TERMINE"));

        // Statistiques financières
        FinancialSnapshotDTO finances = new FinancialSnapshotDTO();
        finances.setBudgetTotal(budgetRepository.sumMontantPrevu());
        finances.setDepensesTotal(factureRepository.sumMontantTotal());
        finances.setBeneficeNet(finances.getBudgetTotal() - finances.getDepensesTotal());
        dashboard.setFinances(finances);

        // Alertes
        AlertDTO alertes = new AlertDTO();
        alertes.setChantiersEnRetard(dashboard.getNombreChantiersEnRetard());
        alertes.setBudgetsDepasses(budgetRepository.countByMontantEngageGreaterThanMontantPrevu());
        alertes.setIncidentsNonResolus(incidentRepository.countByStatutNot("RESOLU"));
        dashboard.setAlertes(alertes);

        return dashboard;
    }

    @Override
    public FinancialReportDTO getFinancialReport(Integer year) {
        FinancialReportDTO report = new FinancialReportDTO();
        report.setAnnee(year);

        // Chiffre d'affaires par mois
        Map<String, Double> caParMois = new LinkedHashMap<>();
        for(int month = 1; month <= 12; month++) {
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
            double ca = factureRepository.sumMontantByDateBetween(start, end);
            caParMois.put(start.getMonth().toString(), ca);
        }
        report.setCaParMois(caParMois);

        // Dépenses par catégorie
        report.setDepensesParCategorie(factureRepository.sumMontantByCategorie(year));

        // Top 5 clients
        report.setTopClients(chantierRepository.findTop5ClientsByCa(year));

        return report;
    }

    @Override
    public List<ChantierStatisticDTO> getChantierStatistics() {
        return chantierRepository.findAll().stream().map(chantier -> {
            ChantierStatisticDTO stat = new ChantierStatisticDTO();
            stat.setChantierId(chantier.getId());
            stat.setReference(chantier.getReference());
            stat.setClient(chantier.getClient());
            stat.setStatut(chantier.getStatut());

            // Avancement
            long totalJalons = chantier.getJalons().size();
            long jalonsTermines = chantier.getJalons().stream()
                    .filter(j -> "TERMINE".equals(j.getStatut()))
                    .count();
            stat.setAvancementJalons(totalJalons > 0 ? (jalonsTermines * 100 / totalJalons) : 0);

            // Budget
            if(chantier.getBudget() != null) {
                stat.setBudgetInitial(chantier.getBudget().getMontantPrevu());
                stat.setDepenses(chantier.getBudget().getMontantEngage());
                stat.setTauxEngagement(stat.getBudgetInitial() > 0 ?
                        (stat.getDepenses() / stat.getBudgetInitial()) * 100 : 0);
            }

            return stat;
        }).collect(Collectors.toList());
    }

    @Override
    public List<IncidentStatisticDTO> getIncidentStatistics(LocalDate debut, LocalDate fin) {
        return incidentRepository.findByDateBetween(debut, fin).stream()
                .map(incident -> {
                    IncidentStatisticDTO stat = new IncidentStatisticDTO();
                    stat.setType(incident.getType());
                    stat.setDate(incident.getDate());
                    stat.setStatut(incident.getStatut());
                    stat.setGravite(incident.getGravite());

                    if(incident.getChantier() != null) {
                        stat.setChantierReference(incident.getChantier().getReference());
                    }

                    if(incident.getUtilisateur() != null) {
                        stat.setUtilisateurNom(incident.getUtilisateur().getNomComplet());
                    }

                    return stat;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PerformanceReportDTO getPerformanceReport() {
        PerformanceReportDTO report = new PerformanceReportDTO();

        // Temps moyen de résolution des incidents
        report.setTempsMoyenResolution(incidentRepository.avgResolutionTime());

        // Taux de satisfaction client
        report.setTauxSatisfaction(chantierRepository.avgClientSatisfaction());

        // Productivité par employé
        report.setProductiviteParEmploye(utilisateurRepository.findAll().stream()
                .collect(Collectors.toMap(
                        u -> u.getNom() + " " + u.getPrenom(),
                        u -> u.getChantiers().stream()
                                .mapToDouble(c -> c.getBudget() != null ? c.getBudget().getMontantRealise() : 0)
                                .sum()
                )));

        return report;
    }
}
