package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfigurationProperties.class)
public class SreSloDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SreSloDemoApplication.class, args);
  }

}
