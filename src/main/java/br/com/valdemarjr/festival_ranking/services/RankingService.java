package br.com.valdemarjr.festival_ranking.services;

import br.com.valdemarjr.festival_ranking.controllers.RankingFilters;
import br.com.valdemarjr.festival_ranking.controllers.RankingSearch;
import br.com.valdemarjr.festival_ranking.repositories.RankingRepository;
import br.com.valdemarjr.festival_ranking.domain.Ranking;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RankingService {

  private final RankingRepository rankingRepository;

  public RankingService(RankingRepository rankingRepository) {
    this.rankingRepository = rankingRepository;
  }

  public void save(Ranking ranking) {
    rankingRepository.save(ranking);
  }

  public RankingFilters findFilters() {
    return new RankingFilters(
        rankingRepository.findDistinctByGroupNameAndOrderByGroupName(),
        rankingRepository.findDistinctByGenreAndOrderByGenre(),
        rankingRepository.findDistinctBySubGenreAndOrderBySubGenre(),
        rankingRepository.findDistinctByCategoryAndOrderByCategory(),
        rankingRepository.findDistinctByResultAndOrderByResult());
  }

  public List<Ranking> findAll() {
    return rankingRepository.findAll();
  }

  public List<Ranking> findBy(RankingSearch search) {
    return rankingRepository.findAllBy(
        search.groupName(), search.genre(), search.subGenre(), search.category());
  }
}
