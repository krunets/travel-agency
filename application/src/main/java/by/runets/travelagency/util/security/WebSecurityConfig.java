package by.runets.travelagency.util.security;

import by.runets.travelagency.util.encoding.EncodingFilter;
import by.runets.travelagency.util.handler.SuccessUrlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsService userDetailsService;
  @Autowired
  private SuccessUrlHandler successUrlHandler;
  @Autowired
  private EncodingFilter encodingFilter;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	http
		.addFilterBefore(encodingFilter, ChannelProcessingFilter.class);
	http
		.csrf()
		.ignoringAntMatchers("/tour/**")
		.and()
		.authorizeRequests()
		.antMatchers("/admin/home", "/user/home", "/user/review/**").authenticated()
		.antMatchers("/", "/tour*").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.successHandler(successUrlHandler)
		.and()
		.logout().deleteCookies("JSESSIONID")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
		.permitAll()
		.and()
		.rememberMe().key("uniqueAndSecret");
  }
}
