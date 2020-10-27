package fr.jufab.hystrixr4jlab.infrastructure;

import fr.jufab.hystrixr4jlab.infrastructure.github.GithubJufabApi;
import javax.ws.rs.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubJufabAdapterTest {
  public static final String JSON_RESPONSE = "{\"test\": \"test\"}";

  @Mock GithubJufabApi githubJufabApi;

  @InjectMocks
  GithubJufabAdapter githubJufabAdapter;

  @Test
  void shouldExecuteReleaseAndGetResponse() {
    when(githubJufabApi.getAJsonRelease()).thenReturn(JSON_RESPONSE);
    assertThat(githubJufabAdapter.getRelease()).isEqualTo(JSON_RESPONSE);
  }

  @Test
  void shouldExecuteExceptionAndGetException() {
    when(githubJufabApi.getAnException()).thenThrow(BadRequestException.class);
    assertThatThrownBy(() -> githubJufabAdapter.getException()).isInstanceOf(BadRequestException.class);
  }

  //And CircuitBreaker?
}