package fr.jufab.hystrixr4jlab.application.rest;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import fr.jufab.hystrixr4jlab.application.service.GitHubJufabService;
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
public class GitHubReleaseController {

  private GitHubJufabService gitHubJufabService;

  public GitHubReleaseController() {
  }

  @Inject
  public GitHubReleaseController(
      GitHubJufabService gitHubJufabService) {
    this.gitHubJufabService = gitHubJufabService;
  }

  @GET
  public String getGitHubRelease() {
    return this.gitHubJufabService.getJufabRelease();
  }

  @Path("/exception")
  @GET
  public String getAnException() {
    try {
      return this.gitHubJufabService.getAnException();
    } catch (HystrixRuntimeException | HystrixBadRequestException hException) {
      return hException.getClass().getCanonicalName() + " / " + hException.getCause()
          .getClass()
          .getCanonicalName();
    } catch (Exception ex) {
      return ex.getClass().getCanonicalName();
    }
  }
}
