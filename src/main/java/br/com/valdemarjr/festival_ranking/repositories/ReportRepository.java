package br.com.valdemarjr.festival_ranking.repositories;

import br.com.valdemarjr.festival_ranking.domain.Ranking;
import br.com.valdemarjr.festival_ranking.domain.reports.DescriptionNumberReport;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface ReportRepository extends ListCrudRepository<Ranking, Long> {

  @Query("SELECT category as description, COUNT(*) as count FROM rankings GROUP BY category")
  List<DescriptionNumberReport> findCountReportByCategory();

  @Query("SELECT genre as description, COUNT(*) as count FROM rankings GROUP BY genre")
  List<DescriptionNumberReport> findCountReportByGenre();

  @Query("SELECT sub_genre as description, COUNT(*) as count FROM rankings GROUP BY sub_genre")
  List<DescriptionNumberReport> findCountReportBySubGenre();
}
