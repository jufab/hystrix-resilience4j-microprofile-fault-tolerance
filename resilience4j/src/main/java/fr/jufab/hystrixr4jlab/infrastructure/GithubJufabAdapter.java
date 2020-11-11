package fr.jufab.hystrixr4jlab.infrastructure;

import fr.jufab.hystrixr4jlab.domaine.GithubPort;
import fr.jufab.hystrixr4jlab.infrastructure.github.GithubCircuitBreaker;
import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubJufabAdapter implements GithubPort {

  GithubCircuitBreaker githubCircuitBreaker;

  @Inject
  public GithubJufabAdapter(
      GithubCircuitBreaker gitHubCircuitBreaker) {
    this.githubCircuitBreaker = gitHubCircuitBreaker;
  }

  @Override public String getRelease() {
    return this.githubCircuitBreaker.executeJSONRelease();
  }

  @Override public String getException() {
    return this.githubCircuitBreaker.executeException();
  }

  @Override public String getSlowRelease() {
    return this.githubCircuitBreaker.executeSlowlyJSONRelease();
  }
}
