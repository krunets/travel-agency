package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.ICollectionRepository;

public class UserService extends AbstractService<User, Integer> {
	public UserService (ICollectionRepository<User, Integer> repository) {
		super(repository);
	}
}
