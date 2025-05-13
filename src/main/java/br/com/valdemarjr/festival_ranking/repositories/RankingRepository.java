package br.com.valdemarjr.festival_ranking.repositories;

import br.com.valdemarjr.festival_ranking.domain.Ranking;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface RankingRepository extends ListCrudRepository<Ranking, Long> {

  @Cacheable("rankings")
  @Query("SELECT * FROM rankings ORDER BY score DESC")
  List<Ranking> findAllOrderByScoreDesc();

  @Query("SELECT DISTINCT group_name FROM rankings ORDER BY group_name")
  List<String> findDistinctByGroupNameAndOrderByGroupName();

  @Query("SELECT DISTINCT genre FROM rankings ORDER BY genre")
  List<String> findDistinctByGenreAndOrderByGenre();

  @Query("SELECT DISTINCT sub_genre FROM rankings ORDER BY sub_genre")
  List<String> findDistinctBySubGenreAndOrderBySubGenre();

  @Query("SELECT DISTINCT category FROM rankings ORDER BY category")
  List<String> findDistinctByCategoryAndOrderByCategory();

  @Query("SELECT DISTINCT result FROM rankings ORDER BY result")
  List<String> findDistinctByResultAndOrderByResult();

  @Query(
"""
        SELECT * FROM rankings
        WHERE (:groupName = '' OR group_name = :groupName)
        AND (:genre = '' OR genre = :genre)
        AND (:subGenre = '' OR sub_genre = :subGenre)
        AND (:category = '' OR category = :category)
        ORDER BY score DESC
        """)
  List<Ranking> findAllBy(String groupName, String genre, String subGenre, String category);
}
