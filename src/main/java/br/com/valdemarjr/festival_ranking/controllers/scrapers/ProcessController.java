package br.com.valdemarjr.festival_ranking.controllers.scrapers;

import br.com.valdemarjr.festival_ranking.domain.Ranking;
import br.com.valdemarjr.festival_ranking.services.ProcessService;
import br.com.valdemarjr.festival_ranking.services.RankingService;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController {

  private static final Logger log = LoggerFactory.getLogger(ProcessController.class);

  @Value("${scraper.url}")
  private String url;

  private final RankingService rankingService;
  private final ProcessService processService;

  public ProcessController(RankingService rankingService, ProcessService processService) {
    this.rankingService = rankingService;
    this.processService = processService;
  }

  @GetMapping
  String process() throws IOException {
    rankingService.clearRanking();
    var doc = Jsoup.connect(url).get();
    var tBodies = doc.getElementsByTag("tbody");
    var tBody = tBodies.get(2);
    var trs = tBody.getElementsByTag("tr");
    if (!trs.isEmpty()) {
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
      processService.saveProcessedDate();
    }
    return "Process completed";
  }

  @Scheduled(fixedDelay = 1000 * 60 * 60 * 4) // every 4 hours
  void scheduledProcess() throws IOException {
    log.info("Start collecting data from the scraper");
//    process();
    log.info("Finished!");
  }
}
