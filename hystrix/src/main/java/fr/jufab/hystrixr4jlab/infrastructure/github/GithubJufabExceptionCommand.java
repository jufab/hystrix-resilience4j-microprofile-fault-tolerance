package fr.jufab.hystrixr4jlab.infrastructure.github;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import javax.inject.Inject;

/**
 * @author jufab
 * @version 1.0
 */
public class GithubJufabExceptionCommand extends HystrixCommand<String> {

  private GithubJufabApi githubJufabApi;

  @Inject
  protected GithubJufabExceptionCommand(GithubJufabApi githubJufabApi) {
    super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("github"))
        .andCommandKey(HystrixCommandKey.Factory.asKey("github-exception")));
    this.githubJufabApi = githubJufabApi;
  }

  @Override protected String run() {
    return this.githubJufabApi.getAnException();
  }
}
