package br.com.valdemarjr.festival_ranking.controllers;

import java.util.List;

public record RankingFilters(
    List<String> groupNames,
    List<String> genres,
    List<String> subgenres,
    List<String> categories,
    List<String> results) {}
