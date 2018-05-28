package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.IRepository;

public class UserService extends AbstractService<User, Integer> {
	public UserService (IRepository<User, Integer> repository) {
		super(repository);
	}
}
