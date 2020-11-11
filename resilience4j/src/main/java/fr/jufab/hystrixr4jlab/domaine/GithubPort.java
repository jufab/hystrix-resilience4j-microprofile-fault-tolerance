package fr.jufab.hystrixr4jlab.domaine;

/**
 * @author jufab
 * @version 1.0
 */
public interface GithubPort {
  String getRelease();
  String getException();
  String getSlowRelease();
}
