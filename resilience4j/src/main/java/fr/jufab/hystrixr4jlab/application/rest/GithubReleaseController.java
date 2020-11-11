package fr.jufab.hystrixr4jlab.application.rest;

import fr.jufab.hystrixr4jlab.application.service.GithubJufabService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author jufab
 * @version 1.0
 */
@Path("/github")
@RequestScoped
public class GithubReleaseController {

  private GithubJufabService gitHubJufabService;

  public GithubReleaseController() {
  }

  @Inject
  public GithubReleaseController(
      GithubJufabService gitHubJufabService) {
    this.gitHubJufabService = gitHubJufabService;
  }

  @GET
  public String getGitHubRelease() {
      return this.gitHubJufabService.getJufabRelease();
  }

  @Path("/exception")
  @GET
  public String getGitHubException() {
    try {
      return this.gitHubJufabService.getAnException();
    } catch (Exception bad) {
      return bad.getClass().getCanonicalName();
    }
  }

  @Path("/slow")
  @GET
  public String getSlowRelease() {
    try {
      return this.gitHubJufabService.getSlowRelease();
    } catch (Exception bad) {
      return bad.getClass().getCanonicalName();
    }
  }
}
