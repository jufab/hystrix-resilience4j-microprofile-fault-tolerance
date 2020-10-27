package fr.jufab.hystrixr4jlab.infrastructure.github;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubJufabApi {
  private static final String URL_JUFAB_RELEASE =
      "https://api.github.com/repos/jufab/opentelemetry-angular-interceptor/releases";

  public String getAJsonRelease() {
    Client client = ClientBuilder.newClient();
    WebTarget webTarget =
        client.target(URL_JUFAB_RELEASE);
    Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
    Response response = invocationBuilder.get();
    return response.readEntity(String.class);
  }

  public String getAnException() {
    throw new BadRequestException("request");
  }
}
