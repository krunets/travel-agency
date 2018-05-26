package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.repository.impl.AbstractRepository;

public class UserService extends AbstractService<User<Integer>, Integer> {
	public UserService (AbstractRepository<User<Integer>, Integer> repository) {
		super(repository);
	}
}
