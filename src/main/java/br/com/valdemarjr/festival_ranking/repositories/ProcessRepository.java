package br.com.valdemarjr.festival_ranking.repositories;

import br.com.valdemarjr.festival_ranking.domain.Process;
import org.springframework.data.repository.CrudRepository;

public interface ProcessRepository extends CrudRepository<br.com.valdemarjr.festival_ranking.domain.Process, Long> {

    Process findFirstByOrderByIdDesc();
}
