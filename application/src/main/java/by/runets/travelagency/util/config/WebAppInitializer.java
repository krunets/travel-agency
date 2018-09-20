package by.runets.travelagency.util.config;

import by.runets.travelagency.util.security.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  private static final String SPRING_PROFILES_ACTIVE = "spring.profiles.active";
  private static final String ACTIVE_PROFILE = "production";

  @Override
  protected Class<?>[] getRootConfigClasses() {
	return new Class<?>[]{WebAppConfig.class, WebSecurityConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
	return new Class<?>[]{WebAppConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
	return new String[]{"/"};
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
	super.onStartup(servletContext);
	servletContext.setInitParameter(SPRING_PROFILES_ACTIVE, ACTIVE_PROFILE);
  }
}
