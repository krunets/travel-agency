package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, Integer> {
	public UserService (IDatabaseRepository<User, Integer> repository, Class<User> classType) {
		super(repository, classType);
	}
}
