package com.example.demo;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Random;

import static java.lang.Thread.sleep;

@Slf4j
@RestController
public class HelloController {
  private final String LONG_REQUEST_LOG_MESSAGE = "long request {}ms";
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

  @SneakyThrows
  @GetMapping("hello")
  public String hello() {
    if (ZonedDateTime.now().getMinute() % 3 <=0) {
      float randomFloatBetweenZeroAndOne = new Random().nextFloat();
      if (randomFloatBetweenZeroAndOne < 0.5) {
        int ms = 200;
        log.info(LONG_REQUEST_LOG_MESSAGE,ms);
        sleep(ms);
      } else if (randomFloatBetweenZeroAndOne < 0.9) {
        int ms = 400;
        log.info(LONG_REQUEST_LOG_MESSAGE,ms);
        sleep(ms);
      } else {
        int ms = 700;
        log.info(LONG_REQUEST_LOG_MESSAGE,ms);
        sleep(ms);
      }
    }
    return "hello";
  }
}
