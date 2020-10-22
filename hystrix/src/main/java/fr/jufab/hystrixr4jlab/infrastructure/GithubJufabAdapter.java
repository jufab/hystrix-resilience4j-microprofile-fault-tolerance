package fr.jufab.hystrixr4jlab.infrastructure;

import fr.jufab.hystrixr4jlab.domaine.JufabReleasePort;
import fr.jufab.hystrixr4jlab.infrastructure.github.GithubJufabExceptionCommand;
import fr.jufab.hystrixr4jlab.infrastructure.github.GithubJufabReleaseCommand;
import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubJufabAdapter implements JufabReleasePort {

  private GithubJufabReleaseCommand githubJufabReleaseCommand;
  private GithubJufabExceptionCommand githubJufabExceptionCommand;

  @Inject
  public GithubJufabAdapter(
      GithubJufabReleaseCommand githubJufabReleaseCommand,
      GithubJufabExceptionCommand githubJufabExceptionCommand) {
    this.githubJufabReleaseCommand = githubJufabReleaseCommand;
    this.githubJufabExceptionCommand = githubJufabExceptionCommand;
  }

  @Override public String getRelease() {
    return this.githubJufabReleaseCommand.execute();
  }

  @Override public String getException() { return this.githubJufabExceptionCommand.execute(); }
}
