package by.runets.travelagency.util.security;

import by.runets.travelagency.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/*@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/login*")
				.hasAnyRole(Role.ADMIN.name(), Role.MEMBER.name())
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login.ftl")
				.defaultSuccessUrl("/homepage.ftl")
				.failureUrl("/login.ftl?error=true")
				.and()
				.logout()
				.logoutSuccessUrl("/login.ftl");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}*/
	
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("admin")
				.password("admin")
				.roles("USER");
	}
	
	@Override
	protected void configure (HttpSecurity http) throws Exception {
		http
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/login*")
				.anonymous()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/homepage")
				.failureUrl("/login.ftl?error=true")
				.and()
				.logout()
				.logoutSuccessUrl("/login");
	}
}
