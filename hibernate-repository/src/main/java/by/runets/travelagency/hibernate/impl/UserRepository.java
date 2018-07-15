package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends AbstractRepository<User> {
	public UserRepository (SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
