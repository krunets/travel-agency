package by.runets.travelagency.util.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure (WebSecurity web) throws Exception {
		
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/login*")
				.anonymous()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login.ftl")
				.defaultSuccessUrl("/homepage.ftl")
				.failureUrl("/login.ftl?error=true")
				.and()
				.logout().logoutSuccessUrl("/login.ftl");
	}
		
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}
}
