package br.com.valdemarjr.festival_ranking.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("rankings")
public record Ranking(
    @Id Long id,
    Integer groupCode,
    String groupName,
    String choreography,
    String genre,
    String subGenre,
    String category,
    Double score,
    String result) {}
