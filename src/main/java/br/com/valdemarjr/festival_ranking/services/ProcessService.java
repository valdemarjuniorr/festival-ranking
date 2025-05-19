package br.com.valdemarjr.festival_ranking.services;

import br.com.valdemarjr.festival_ranking.domain.Process;
import br.com.valdemarjr.festival_ranking.repositories.ProcessRepository;
import java.time.LocalDateTime;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {
  private final Logger log = LoggerFactory.getLogger(ProcessService.class);
  private final ProcessRepository processRepository;

  public ProcessService(ProcessRepository processRepository) {
    this.processRepository = processRepository;
  }

  public void saveProcessedDate() {
    var process = processRepository.findFirstByOrderByIdDesc();
    if (Objects.isNull(process)) {
      processRepository.save(new Process());
    } else {
      processRepository.save(new Process(process.id(), LocalDateTime.now()));
    }
    log.info("Process saved");
  }

  public Process findLastProcessedAt() {
    var process = processRepository.findFirstByOrderByIdDesc();
    return Objects.isNull(process) ? new Process() : process;
  }
}
