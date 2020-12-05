package com.example.demo;

import io.micrometer.core.annotation.Timed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SreSloDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SreSloDemoApplication.class, args);
  }

  @Timed(percentiles = {0.5, 0.9, 0.99})
  @GetMapping("hello")
  public String hello() {
    return "hello";
  }
}
