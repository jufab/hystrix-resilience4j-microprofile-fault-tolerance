package fr.jufab.hystrixr4jlab.application.service;

import fr.jufab.hystrixr4jlab.domaine.GetAnException;
import fr.jufab.hystrixr4jlab.domaine.GetJufabRelease;
import fr.jufab.hystrixr4jlab.domaine.GetSlowRelease;
import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubJufabService {
  private GetJufabRelease getJufabRelease;
  private GetAnException getAnException;
  private GetSlowRelease getSlowRelease;

  @Inject
  public GithubJufabService(GetJufabRelease getJufabRelease,
      GetAnException getAnException, GetSlowRelease getSlowRelease) {
    this.getJufabRelease = getJufabRelease;
    this.getAnException = getAnException;
    this.getSlowRelease = getSlowRelease;
  }

  public String getJufabRelease() {
    return this.getJufabRelease.execute();
  }

  public String getAnException() {
    return this.getAnException.execute();
  }

  public String getSlowRelease() {
    return this.getSlowRelease.execute();
  }
}
