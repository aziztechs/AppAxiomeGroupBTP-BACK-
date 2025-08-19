package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;


import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.ReportingController;
import aziztechs.sn.appaxiomegroupbtpback.dto.*;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.ReportingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ReportingControllerImpl implements ReportingController {

    private final ReportingService reportingService;

    public ReportingControllerImpl(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @Override
    public ResponseEntity<DashboardSummaryDTO> getDashboardSummary() {
        DashboardSummaryDTO response = reportingService.getDashboardSummary();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<FinancialReportDTO> getFinancialReport(@RequestParam(required = false) Integer year) {
        int reportYear = (year != null) ? year : LocalDate.now().getYear();
        FinancialReportDTO response = reportingService.getFinancialReport(reportYear);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ChantierStatisticDTO>> getChantierStatistics() {
        List<ChantierStatisticDTO> response = reportingService.getChantierStatistics();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<IncidentStatisticDTO>> getIncidentStatistics(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin) {
        List<IncidentStatisticDTO> response = reportingService.getIncidentStatistics(debut, fin);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PerformanceReportDTO> getPerformanceReport() {
        PerformanceReportDTO response = reportingService.getPerformanceReport();
        return ResponseEntity.ok(response);
    }
}
