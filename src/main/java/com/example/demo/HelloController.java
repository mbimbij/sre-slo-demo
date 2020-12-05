package com.example.demo;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Duration;

@RestController
public class HelloController {
  @Autowired
  private ApplicationConfigurationProperties applicationConfigurationProperties;
  @Autowired
  MeterRegistry meterRegistry;

  @PostConstruct
  public void customizeMeterRegistry() {
    meterRegistry.config().meterFilter(new MeterFilter() {
      @Override
      public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
        return DistributionStatisticConfig.builder()
            .expiry(Duration.ofSeconds(applicationConfigurationProperties.getPercentilesWindowDurationSeconds()))
            .build()
            .merge(config);
      }
    });
  }

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }
}
