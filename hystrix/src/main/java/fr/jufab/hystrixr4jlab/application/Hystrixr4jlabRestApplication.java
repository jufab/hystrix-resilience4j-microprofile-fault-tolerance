package fr.jufab.hystrixr4jlab.application;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.strategy.HystrixPlugins;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 */
@ApplicationPath("/hystrix")
public class Hystrixr4jlabRestApplication extends Application {

  public Hystrixr4jlabRestApplication() throws ConfigurationException {
    super();
    HystrixPlugins.reset();
    ConfigurationManager.loadPropertiesFromConfiguration(
        new PropertiesConfiguration("META-INF/microprofile-config.properties"));
  }
}
