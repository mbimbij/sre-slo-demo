package com.example.demo

;

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
import scala.util.Random;

class LoadTest extends Simulation {

  val peakLoad = 10;
  val rampUpSeconds = 5;
  val concurrentUsers = 1
  val totalDurationSeconds = 6000;
  val maxThinkTimeMillis = 5000;

  val httpProtocols = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml,application/json;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("actuator health").forever("loopCount") {
    exec(
      http("/hello")
        .get("/hello")
    ).pause(0, maxThinkTimeMillis.milliseconds)
  }

  setUp(scn.inject(
    constantUsersPerSec(concurrentUsers) during(totalDurationSeconds seconds)
  ))
    .protocols(httpProtocols)
    .maxDuration(totalDurationSeconds seconds)
}
