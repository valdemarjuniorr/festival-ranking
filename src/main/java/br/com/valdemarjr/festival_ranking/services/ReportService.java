package br.com.valdemarjr.festival_ranking.services;

import br.com.valdemarjr.festival_ranking.domain.reports.DescriptionNumberReport;
import br.com.valdemarjr.festival_ranking.repositories.ReportRepository;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  private final ReportRepository repository;

  public ReportService(ReportRepository repository) {
    this.repository = repository;
  }

  @Cacheable("report-by-category")
  public List<DescriptionNumberReport> reportByCategory() {
    return repository.findCountReportByCategory();
  }

  @Cacheable("report-by-genre")
  public List<DescriptionNumberReport> reportByGenre() {
    return repository.findCountReportByGenre();
  }

  @Cacheable("report-by-sub-genre")
  public List<DescriptionNumberReport> reportBySubGenre() {
    return repository.findCountReportBySubGenre();
  }
}
