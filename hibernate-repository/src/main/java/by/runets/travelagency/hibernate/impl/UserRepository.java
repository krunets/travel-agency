package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.User;
import by.runets.travelagency.hibernate.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository extends AbstractRepository<User> implements IUserRepository<User, String> {
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserRepository (SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Optional<User> findUserByLogin (String login) {
		Session session = sessionFactory.getCurrentSession();
		return Optional.ofNullable(session.get(User.class, login));
	}
}
