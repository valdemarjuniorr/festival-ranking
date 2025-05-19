package br.com.valdemarjr.festival_ranking.services;

import br.com.valdemarjr.festival_ranking.controllers.RankingFilters;
import br.com.valdemarjr.festival_ranking.controllers.RankingSearch;
import br.com.valdemarjr.festival_ranking.domain.Ranking;
import br.com.valdemarjr.festival_ranking.repositories.RankingRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

  private final RankingRepository rankingRepository;
  private final ReportService reportService;

  public RankingService(RankingRepository rankingRepository, ReportService reportService) {
    this.rankingRepository = rankingRepository;
    this.reportService = reportService;
  }

  public void save(Ranking ranking) {
    rankingRepository.save(ranking);
  }

  @Cacheable("ranking-filters")
  public RankingFilters findFilters() {
    reportService.reportByCategory();
    return new RankingFilters(
        rankingRepository.findDistinctByGroupNameAndOrderByGroupName(),
        rankingRepository.findDistinctByGenreAndOrderByGenre(),
        rankingRepository.findDistinctBySubGenreAndOrderBySubGenre(),
        rankingRepository.findDistinctByCategoryAndOrderByCategory(),
        rankingRepository.findDistinctByResultAndOrderByResult());
  }

  public List<Ranking> findFirstThousandRanking() {
    return rankingRepository.findFirst200ByOrderByScoreDesc();
  }

  public List<Ranking> findAll() {
    return rankingRepository.findAllOrderByScoreDesc();
  }

  public List<Ranking> findBy(RankingSearch search) {
    if (Objects.nonNull(search) && search.isFilterEmpty()) {
      return this.findFirstThousandRanking();
    }
    return rankingRepository.findAllBy(
        search.groupName(), search.genre(), search.subGenre(), search.category());
  }

  public void clearRanking() {
    rankingRepository.deleteAll();
  }
}
