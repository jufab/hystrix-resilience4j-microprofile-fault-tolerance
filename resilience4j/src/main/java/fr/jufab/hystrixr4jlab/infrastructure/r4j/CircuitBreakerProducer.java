package fr.jufab.hystrixr4jlab.infrastructure.r4j;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.Duration;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.ws.rs.BadRequestException;

/**
 * @author jufab
 * @version 1.0
 */
@ApplicationScoped
public class CircuitBreakerProducer {
  CircuitBreakerRegistry circuitBreakerRegistry;

  public CircuitBreakerProducer() {
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
        .failureRateThreshold(50)
        .waitDurationInOpenState(Duration.ofSeconds(5))
        .minimumNumberOfCalls(4)
        .recordExceptions(BadRequestException.class)
        .build();
    this.circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
  }

  @Produces
  public CircuitBreakerRegistry produceCircuitBreakerRegistry() {
    return this.circuitBreakerRegistry;
  }
}
