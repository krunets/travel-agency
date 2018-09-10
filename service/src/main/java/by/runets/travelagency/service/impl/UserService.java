package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Role;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_PAGINATION_SIZE;
import static by.runets.travelagency.util.constant.PaginationConstant.DEFAULT_USER_PAGINATION;

@Service
public class UserService extends AbstractService<User, Long> implements IUserService<User, Long> {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserService (Class<User> classType, IDatabaseRepository<User, Long> abstractRepository) {
		super(classType, abstractRepository);
	}
	
	@Override
	@Transactional
	public boolean registerUserAccount (User user) {
		long userId = 0;
		boolean isEmpty = super
				.readAllByField(FIND_BY_LOGIN, LOGIN_FIELD,
						user.getLogin(), DEFAULT_USER_PAGINATION)
				.isEmpty();
		if (isEmpty) {
			user.setRole(Role.MEMBER);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userId = super.create(user);
		}
		return userId != 0;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> readUserByRole () {
		return super
				.readAllByField(FIND_BY_ROLE, ROLE_FIELD,
						Role.MEMBER, DEFAULT_PAGINATION_SIZE);
	}
}
