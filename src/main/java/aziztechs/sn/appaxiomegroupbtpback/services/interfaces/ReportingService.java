package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReportingService {
    Map<String, Double> getBudgetStatistics();
    List<ChantierStatisticDTO> getChantierStatistics();
    List<IncidentStatisticDTO> getIncidentStatistics(LocalDate startDate, LocalDate endDate);
    FinancialReportDTO getFinancialReport(Integer year);
    DashboardSummaryDTO getDashboardSummary();

    PerformanceReportDTO getPerformanceReport();
}
