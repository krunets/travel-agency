package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.IDatabaseRepository;

public class UserService extends AbstractService<User, Integer> {
	public UserService (IDatabaseRepository<User, Integer> repository) {
		super(repository);
	}
}
