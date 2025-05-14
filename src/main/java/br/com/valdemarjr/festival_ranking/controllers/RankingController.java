package br.com.valdemarjr.festival_ranking.controllers;

import br.com.valdemarjr.festival_ranking.services.RankingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RankingController {

  private final RankingService rankingService;

  public RankingController(RankingService rankingService) {
    this.rankingService = rankingService;
  }

  @GetMapping
  String home(Model model) {
    model.addAttribute("filters", rankingService.findFilters());
    model.addAttribute("rankings", rankingService.findFirstThousandRanking());
    return "index";
  }

  @PostMapping("/search")
  String getRanking(Model model, RankingSearch search) {
    var result = rankingService.findBy(search);
    model.addAttribute("rankings", result);

    return "ranking-table";
  }
}
