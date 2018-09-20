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

import static by.runets.travelagency.util.constant.NamedQueryConstant.FIND_BY_LOGIN;
import static by.runets.travelagency.util.constant.NamedQueryConstant.LOGIN_FIELD;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_USER_PAGINATION;
import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@AllArgsConstructor
public class UserDetailsSecurityService implements UserDetailsService {
  @Autowired
  private IDatabaseRepository<User, Long> userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
	return userRepository.readByNameQuery(FIND_BY_LOGIN, LOGIN_FIELD, login, 0, DEFAULT_USER_PAGINATION)
		.get(0)
		.map(user -> withUsername(login)
			.password(user.getPassword())
			.roles(user.getRole().name())
			.build())
		.orElseThrow(ResourceNotFoundException::new);
  }
}
