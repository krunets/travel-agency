package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.impl.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {
	public UserService (UserRepository userRepository, Class<User> classType) {
		super(userRepository, classType);
	}
}
