package br.com.valdemarjr.festival_ranking.controllers;

import org.springframework.util.StringUtils;

import java.util.Objects;

public record RankingSearch(String groupName, String genre, String subGenre, String category) {

  public Boolean isFilterEmpty() {
    return !StringUtils.hasText(groupName)
        && !StringUtils.hasText(genre)
        && !StringUtils.hasText(subGenre)
        && !StringUtils.hasText(category);
  }
}
