package by.runets.travelagency.util.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@Component
public class SuccessUrlHandler extends SimpleUrlAuthenticationSuccessHandler {
  @Autowired
  private RedirectStrategy redirectStrategy;

  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	String targetUrl = determineTargetUrl(authentication);
	redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  private String determineTargetUrl(Authentication authentication) {
	String url = "";

	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	List<String> roles = new ArrayList<String>();

	for (GrantedAuthority a : authorities) {
	  roles.add(a.getAuthority());
	}
	log.error(roles.toString());
	return isAdmin(roles) || isUser(roles) ? "/" : "/accessDenied";
  }

  private boolean isUser(List<String> roles) {
	return roles.contains("ROLE_MEMBER");
  }

  private boolean isAdmin(List<String> roles) {
	return roles.contains("ROLE_ADMIN");
  }
}
