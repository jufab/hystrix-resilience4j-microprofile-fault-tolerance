package fr.jufab.hystrixr4jlab.infrastructure;

import fr.jufab.hystrixr4jlab.domaine.GithubPort;
import fr.jufab.hystrixr4jlab.infrastructure.github.GithubJufabApi;
import javax.inject.Inject;

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

  @Override public String getRelease() {
    return this.githubJufabApi.getAJsonRelease();
  }

  @Override public String getException() {
    return this.githubJufabApi.getAnException();
  }
}
