package br.com.valdemarjr.festival_ranking.controllers.scrapers;

import br.com.valdemarjr.festival_ranking.domain.reports.ReportGroup;
import br.com.valdemarjr.festival_ranking.services.ProcessService;
import br.com.valdemarjr.festival_ranking.services.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportController {

  private final ReportService reportService;
  private final ProcessService processService;

  public ReportController(ReportService reportService, ProcessService processService) {
    this.reportService = reportService;
      this.processService = processService;
  }

  @GetMapping
  String reports(Model model) {
    model.addAttribute("reportsByGenre", new ReportGroup(reportService.reportByGenre()));
    model.addAttribute("reportsBySubgenre", new ReportGroup(reportService.reportBySubGenre()));
    model.addAttribute("reports", new ReportGroup(reportService.reportByCategory()));
    model.addAttribute("processedAt", processService.findLastProcessedAt().processedDateAsString());

    return "reports";
  }
}
