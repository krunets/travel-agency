package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@AllArgsConstructor
public class UserDetailsSecurityService implements UserDetailsService {
	
	private static final String NAMED_QUERY = "FIND_BY_LOGIN";
	private static final String FIELD = "login";
	
	@Autowired
	private IDatabaseRepository<User, Long> userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername (String login) throws UsernameNotFoundException {
		return userRepository.readByNameQuery(NAMED_QUERY, FIELD, login)
				.get(0)
				.map(user -> withUsername(login)
						.password(user.getPassword())
						.roles(user.getRole().name())
						.build())
				.orElseThrow(ResourceNotFoundException::new);
	}
}
