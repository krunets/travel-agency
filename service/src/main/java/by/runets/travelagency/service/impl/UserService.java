package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Role;
import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends AbstractService<User, Long> implements IUserService<User, Long> {
	private static final int DEFAULT_USER_PAGINATION = 1;
	private static final String NAMED_QUERY = "FIND_BY_LOGIN";
	private static final String FIELD = "login";
	
	@Autowired
	private IDatabaseRepository<User, Long> userRepositroy;
	
	public UserService (Class<User> classType, IDatabaseRepository<User, Long> abstractRepository) {
		super(classType, abstractRepository);
		this.userRepositroy = abstractRepository;
	}
	
	@Override
	@Transactional
	public boolean registerUserAccount (User user) {
		long userId = 0;
		boolean isEmpty = userRepositroy
				.readByNameQuery(NAMED_QUERY, FIELD, user.getLogin(), DEFAULT_USER_PAGINATION)
				.isEmpty();
		
		if (isEmpty) {
			user.setRole(Role.MEMBER);
			userId = userRepositroy.create(user);
		}
		return userId != 0;
	}
}
