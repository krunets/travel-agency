package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, Long>  {
	public UserService (Class<User> classType, IDatabaseRepository<User, Long> abstractRepository) {
		super(classType, abstractRepository);
	}
}
