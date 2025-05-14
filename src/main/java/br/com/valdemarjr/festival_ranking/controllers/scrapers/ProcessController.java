package br.com.valdemarjr.festival_ranking.controllers.scrapers;

import br.com.valdemarjr.festival_ranking.domain.Ranking;
import br.com.valdemarjr.festival_ranking.services.RankingService;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController {

  @Value("${scraper.url}")
  private String url;

  private final RankingService rankingService;

  public ProcessController(RankingService rankingService) {
    this.rankingService = rankingService;
  }

  @GetMapping
  String process() throws IOException {
     rankingService.clearRanking();
    var doc = Jsoup.connect(url).get();
    var tBodies = doc.getElementsByTag("tbody");
    var tBody = tBodies.get(2);
    var trs = tBody.getElementsByTag("tr");
    trs.stream()
        .skip(1)
        .forEach(
            tr -> {
              var tds = tr.getElementsByTag("td");
              var groupCode = tds.get(0).text().split(" - ")[0];
              var groupName = tds.get(0).text().split(" - ")[1];
              var choreography = tds.get(1).text();
              var genre = tds.get(2).text();
              var subgenre = tds.get(3).text();
              var category = tds.get(4).text();
              var score = tds.get(5).text().replace(",", ".");
              var result = tds.get(6).text();

              rankingService.save(
                  new Ranking(
                      null,
                      Integer.valueOf(groupCode),
                      groupName,
                      choreography,
                      genre,
                      subgenre,
                      category,
                      Double.parseDouble(score),
                      result));
            });
    return "Process completed";
  }
}
