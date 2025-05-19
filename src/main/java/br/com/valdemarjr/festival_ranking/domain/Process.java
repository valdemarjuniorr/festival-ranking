package br.com.valdemarjr.festival_ranking.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;

public record Process(@Id Long id, LocalDateTime processedAt) {

  public Process() {
    this(null, LocalDateTime.now());
  }

  public String processedDateAsString() {
    return processedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
  }
}
