package fr.jufab.hystrixr4jlab.domaine;

import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GetAnException {
  private GithubPort githubPort;

  @Inject
  public GetAnException(GithubPort githubPort) {
    this.githubPort = githubPort;
  }

  public String execute() {
    return this.githubPort.getException();
  }
}
