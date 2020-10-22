package fr.jufab.hystrixr4jlab.domaine;

import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GetAnException {
  private JufabReleasePort jufabReleasePort;

  @Inject
  public GetAnException(JufabReleasePort jufabReleasePort) {
    this.jufabReleasePort = jufabReleasePort;
  }

  public String execute() {
    return this.jufabReleasePort.getException();
  }
}
