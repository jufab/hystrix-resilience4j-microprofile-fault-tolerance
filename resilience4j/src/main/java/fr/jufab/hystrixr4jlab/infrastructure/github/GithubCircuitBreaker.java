package fr.jufab.hystrixr4jlab.infrastructure.github;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.Duration;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubCircuitBreaker {
  Logger logger = LoggerFactory.getLogger(GithubCircuitBreaker.class);
  CircuitBreakerRegistry circuitBreakerRegistry;
  GithubJufabApi githubJufabApi;

  @Inject
  public GithubCircuitBreaker(
      CircuitBreakerRegistry circuitBreakerRegistry, GithubJufabApi githubJufabApi) {
    this.circuitBreakerRegistry = circuitBreakerRegistry;
    this.githubJufabApi = githubJufabApi;
  }

  public String executeJSONRelease() {
    CircuitBreaker circuitBreaker = this.circuitBreakerRegistry.circuitBreaker("github-release");
    circuitBreaker.getEventPublisher()
        .onEvent(event -> logger.info("Event :" + event));
    return circuitBreaker
        .executeSupplier(githubJufabApi::getAJsonRelease);
  }

  public String executeException() {
    CircuitBreaker circuitBreaker = this.circuitBreakerRegistry.circuitBreaker("github-ex");
    circuitBreaker.getEventPublisher()
        .onEvent(event -> logger.info("Event :" + event));
    return circuitBreaker
        .executeSupplier(githubJufabApi::getAnException);
  }

  public String executeSlowlyJSONRelease() {
    CircuitBreaker circuitBreaker = this.circuitBreakerRegistry
        .circuitBreaker("github-slow",
            CircuitBreakerConfig.from(this.circuitBreakerRegistry.getDefaultConfig())
                .slowCallDurationThreshold(Duration.ofSeconds(3))
                .slowCallRateThreshold(25).build());
    circuitBreaker.getEventPublisher()
        .onEvent(event -> logger.info("Event : " + event));
    return circuitBreaker
        .executeSupplier(githubJufabApi::getASlowlyJsonRelease);
  }
}
