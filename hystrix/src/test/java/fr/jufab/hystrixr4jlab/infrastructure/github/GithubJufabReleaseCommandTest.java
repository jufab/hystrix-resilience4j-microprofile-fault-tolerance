package fr.jufab.hystrixr4jlab.infrastructure.github;

import com.netflix.config.ConfigurationManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubJufabReleaseCommandTest {

  public static final String JSON_RESPONSE = "{\"test\": \"test\"}";

  @Mock
  GithubJufabApi githubJufabApi;

  @InjectMocks
  GithubJufabReleaseCommand githubJufabReleaseCommand;

  @Test
  @DisplayName("Should execute Hystrix Command and get a response string")
  void shouldExecuteCommandAndGetAResponseString() {
    when(githubJufabApi.getAJsonRelease()).thenReturn(JSON_RESPONSE);
    assertThat(githubJufabReleaseCommand.execute()).isEqualTo(JSON_RESPONSE);
  }

  @Test
  @DisplayName("Should Execute FallBack on Hystrix Command and return fallback message")
  void shouldExecuteFallbackMethodAndReturnFallbackString() {
    ConfigurationManager.getConfigInstance()
        .setProperty("hystrix.command.github-release.circuitBreaker.forceOpen", true);
    assertThat(githubJufabReleaseCommand.execute()).isEqualTo("fallback");
    ConfigurationManager.getConfigInstance()
        .setProperty("hystrix.command.github-release.circuitBreaker.forceOpen", false);
  }
}