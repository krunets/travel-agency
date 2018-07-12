package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.annotation.Loggable;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AbstractRepository<T> implements IDatabaseRepository<T, Long> {
	private static final String READ_ALL_QUERY = "SELECT * FROM ";
	
	@Autowired
	protected final SessionFactory sessionFactory;
	
	@Loggable
	@Override
	public void create (T entity) {
		sessionFactory.getCurrentSession().persist(entity);
	}
	
	@Override
	public List<Optional<T>> readAll (Class<T> classType) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createNativeQuery(READ_ALL_QUERY + classType, classType);
		List<T> queryResultList = query.getResultList();
		return queryResultList.stream()
				.map(Optional::ofNullable)
				.collect(Collectors.toList());
	}
	
	@Loggable
	@Override
	public Optional<T> read (final Class<T> classType, final Long id) {
		return Optional.of(sessionFactory.getCurrentSession().get(classType, id));
	}
	
	@Loggable
	@Override
	public void update (final T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
	
	@Loggable
	@Override
	public void delete (final T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}
}
