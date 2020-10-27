package fr.jufab.hystrixr4jlab.infrastructure;

import fr.jufab.hystrixr4jlab.domaine.GithubPort;
import fr.jufab.hystrixr4jlab.infrastructure.github.GithubJufabApi;
import javax.inject.Inject;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubJufabAdapter implements GithubPort {

  GithubJufabApi githubJufabApi;

  @Inject
  public GithubJufabAdapter(
      GithubJufabApi githubJufabApi) {
    this.githubJufabApi = githubJufabApi;
  }

  //@Fallback(fallbackMethod = "doFallback")
  @CircuitBreaker(requestVolumeThreshold = 4)
  @Override public String getRelease() {
    return this.githubJufabApi.getAJsonRelease();
  }

  //@Fallback(fallbackMethod = "doFallback")
  @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio = 0.5, delay = 5000)
  @Override public String getException() {
    return this.githubJufabApi.getAnException();
  }

  /*private String doFallback() {
    return "fallback";
  }*/
}
