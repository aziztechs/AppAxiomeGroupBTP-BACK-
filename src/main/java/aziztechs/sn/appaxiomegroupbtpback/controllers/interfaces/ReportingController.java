package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;


import aziztechs.sn.appaxiomegroupbtpback.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Reporting", description = "API pour les rapports et statistiques")
@RequestMapping("/api/reporting")
public interface ReportingController {

    @Operation(summary = "Tableau de bord avec les indicateurs clés")
    @GetMapping("/dashboard")
    ResponseEntity<DashboardSummaryDTO> getDashboardSummary();

    @Operation(summary = "Rapport financier annuel")
    @GetMapping("/finances")
    ResponseEntity<FinancialReportDTO> getFinancialReport(
            @RequestParam(required = false) Integer year);

    @Operation(summary = "Statistiques des chantiers")
    @GetMapping("/chantiers/stats")
    ResponseEntity<List<ChantierStatisticDTO>> getChantierStatistics();

    @Operation(summary = "Statistiques des incidents")
    @GetMapping("/incidents/stats")
    ResponseEntity<List<IncidentStatisticDTO>> getIncidentStatistics(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin);

    @Operation(summary = "Rapport de performance")
    @GetMapping("/performance")
    ResponseEntity<PerformanceReportDTO> getPerformanceReport();
}
