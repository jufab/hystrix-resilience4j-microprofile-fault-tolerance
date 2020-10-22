package fr.jufab.hystrixr4jlab.domaine;

import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GetJufabRelease {

  private JufabReleasePort jufabReleasePort;

  @Inject
  public GetJufabRelease(JufabReleasePort jufabReleasePort) {
    this.jufabReleasePort = jufabReleasePort;
  }

  public String execute() {
    return this.jufabReleasePort.getRelease();
  }
}
