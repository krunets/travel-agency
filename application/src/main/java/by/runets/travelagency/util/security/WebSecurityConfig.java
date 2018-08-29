package by.runets.travelagency.util.security;

import by.runets.travelagency.util.handler.SuccessUrlHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SuccessUrlHandler successUrlHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService);
				/*.passwordEncoder(passwordEncoder());*/
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/admin_home", "user_home").authenticated()
				.antMatchers("/", "/tour*").permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.successHandler(successUrlHandler)
/*
				.defaultSuccessUrl("/homepage")
*/
				.and()
				.logout().deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.permitAll()
				.and()
				.rememberMe().key("uniqueAndSecret");
	}
}
