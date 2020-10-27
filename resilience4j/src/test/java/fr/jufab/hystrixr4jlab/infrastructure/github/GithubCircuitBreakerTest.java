package fr.jufab.hystrixr4jlab.infrastructure.github;

import fr.jufab.hystrixr4jlab.infrastructure.r4j.CircuitBreakerProducer;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import javax.ws.rs.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubCircuitBreakerTest {
  public static final String JSON_RESPONSE = "{\"test\": \"test\"}";

  CircuitBreakerProducer circuitBreakerProducer = new CircuitBreakerProducer();
  @Mock
  CircuitBreakerRegistry circuitBreakerRegistry;
  @Mock
  GithubJufabApi githubJufabApi;
  @InjectMocks
  GithubCircuitBreaker githubCircuitBreaker;

  @Test
  @DisplayName("Should execute circuit breaker and return a response")
  void shouldExecuteCricuitBreakerAndReturnAResponse() {
    when(githubJufabApi.getAJsonRelease()).thenReturn(JSON_RESPONSE);
    when(circuitBreakerRegistry.circuitBreaker(anyString())).thenReturn(circuitBreakerProducer.produceCircuitBreakerRegistry().circuitBreaker("circuit-mock"));
    assertThat(githubCircuitBreaker.executeJSONRelease()).isEqualTo(JSON_RESPONSE);
  }

  @Test
  @DisplayName("Should execute circuit breaker force open and return a CallNotPermittedException")
  void shouldExecuteCricuitBreakerForceOpenAndReturnAnException() {
    CircuitBreaker circuitBreakerMock = circuitBreakerProducer.produceCircuitBreakerRegistry().circuitBreaker("circuit-mock");
    circuitBreakerMock.transitionToForcedOpenState();
    when(circuitBreakerRegistry.circuitBreaker(anyString())).thenReturn(circuitBreakerMock);
    assertThatThrownBy(()->githubCircuitBreaker.executeJSONRelease()).isInstanceOf(
        CallNotPermittedException.class);
  }

  @Test
  @DisplayName("Should execute circuit breaker and return a BadRequestException")
  void shouldExecuteCricuitBreakerAndReturnAnException() {
    when(githubJufabApi.getAnException()).thenThrow(BadRequestException.class);
    when(circuitBreakerRegistry.circuitBreaker(anyString())).thenReturn(circuitBreakerProducer.produceCircuitBreakerRegistry().circuitBreaker("circuit-mock"));
    assertThatThrownBy(()->githubCircuitBreaker.executeException()).isInstanceOf(
        BadRequestException.class);
  }

  @Test
  @DisplayName("Should execute circuit breaker force open, execute exception and return a CallNotPermittedException")
  void shouldExecuteCricuitBreakerForceOpenExecuteExceptionAndReturnACallNotPermittedException() {
    CircuitBreaker circuitBreakerMock = circuitBreakerProducer.produceCircuitBreakerRegistry().circuitBreaker("circuit-mock");
    circuitBreakerMock.transitionToForcedOpenState();
    when(circuitBreakerRegistry.circuitBreaker(anyString())).thenReturn(circuitBreakerMock);
    assertThatThrownBy(()->githubCircuitBreaker.executeException()).isInstanceOf(
        CallNotPermittedException.class);
  }
}