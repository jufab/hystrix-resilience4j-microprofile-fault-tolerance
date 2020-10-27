package fr.jufab.hystrixr4jlab.infrastructure.github;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GithubJufabExceptionCommandTest {

  GithubJufabExceptionCommand githubJufabExceptionCommand;

  @BeforeEach
  void init() {
    githubJufabExceptionCommand = new GithubJufabExceptionCommand(new GithubJufabApi());
  }

  @Test
  @DisplayName("Should execute Hystrix Command and get a HystrixRuntimeException")
  void shouldExecuteCommandAndGetAHystrixRuntimeException() {
    assertThatThrownBy(() -> githubJufabExceptionCommand.execute()).isInstanceOf(
        HystrixRuntimeException.class);
  }

  @Test
  @DisplayName("Should execute Hystrix Command and get a HystrixRuntimeException with circuit breaker open")
  void shouldExecuteCommandAndGetHystrixRuntimeExceptionWithCircuitBreakerOpen() {
    ConfigurationManager.getConfigInstance()
        .setProperty("hystrix.command.github-release.circuitBreaker.forceOpen", true);
    assertThatThrownBy(() -> githubJufabExceptionCommand.execute()).isInstanceOf(
        HystrixRuntimeException.class);
    ConfigurationManager.getConfigInstance()
        .setProperty("hystrix.command.github-release.circuitBreaker.forceOpen", false);
  }
}