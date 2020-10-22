package fr.jufab.hystrixr4jlab.infrastructure.r4j;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * @author jufab
 * @version 1.0
 */
@ApplicationScoped
public class CircuitBreakerProducer {
  CircuitBreakerRegistry circuitBreakerRegistry;

  public CircuitBreakerProducer() {
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
        .failureRateThreshold(100)
        .waitDurationInOpenState(Duration.ofMillis(5000))
        .slowCallDurationThreshold(Duration.ofMillis(60000))
        .permittedNumberOfCallsInHalfOpenState(5)
        .minimumNumberOfCalls(3)
        .slidingWindowSize(1)
        .recordExceptions(IOException.class, TimeoutException.class, WebApplicationException.class)
        .build();
    this.circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
  }

  @Produces
  public CircuitBreakerRegistry produceCircuitBreakerRegistry() {
    return this.circuitBreakerRegistry;
  }
}
