package fr.jufab.hystrixr4jlab.infrastructure.github;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubJufabReleaseCommand extends HystrixCommand<String> {

  private GithubJufabApi githubJufabApi;

  @Inject
  protected GithubJufabReleaseCommand(GithubJufabApi githubJufabApi) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("github"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("github-release")));
    this.githubJufabApi = githubJufabApi;
  }

  @Override protected String run() {
    return this.githubJufabApi.getAJsonRelease();
  }

  @Override protected String getFallback() {
    return "fallback";
  }
}
