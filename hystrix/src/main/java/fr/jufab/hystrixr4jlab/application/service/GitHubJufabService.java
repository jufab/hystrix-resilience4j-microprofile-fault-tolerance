package fr.jufab.hystrixr4jlab.application.service;

import fr.jufab.hystrixr4jlab.domaine.GetAnException;
import fr.jufab.hystrixr4jlab.domaine.GetJufabRelease;
import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GitHubJufabService {
  private GetJufabRelease getJufabRelease;
  private GetAnException getAnException;

  @Inject
  public GitHubJufabService(GetJufabRelease getJufabRelease, GetAnException getAnException) {
    this.getJufabRelease = getJufabRelease;
    this.getAnException = getAnException;
  }

  public String getJufabRelease() {
    return this.getJufabRelease.execute();
  }

  public String getAnException() {
    return this.getAnException.execute();
  }
}
