package br.com.valdemarjr.festival_ranking.domain.reports;

import java.util.List;
import java.util.stream.Collectors;

public record ReportGroup(List<DescriptionNumberReport> reports) {

  public List<String> getLabels() {
    return this.reports.stream().map(DescriptionNumberReport::description).toList();
  }

  public List<Integer> getCounts() {
    return this.reports.stream().map(DescriptionNumberReport::count).toList();
  }
}
