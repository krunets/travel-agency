package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.hibernate.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private IUserRepository<User, String> userRepository;
	
	@Override
	public UserDetails loadUserByUsername (String login) throws UsernameNotFoundException {
		return userRepository.findUserByLogin(login)
				.map(user -> withUsername(login)
						.password(user.getPassword())
						.roles(user.getRole().name())
						.build())
				.orElseThrow(ResourceNotFoundException::new);
	}
}
