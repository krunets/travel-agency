package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.util.annotation.Loggable;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public abstract class AbstractRepository<T> implements IDatabaseRepository<T, Long> {
	private static final String READ_ALL_QUERY = "SELECT * FROM ";
	
	@Autowired
	private final SessionFactory sessionFactory;
	
	@Loggable
	@Override
	public Long create (T entity) {
		return (Long) sessionFactory.getCurrentSession().save(entity);
	}
	
	@Override
	public List<Optional<T>> readAll (final Class<T> classType) {
		Table table = classType.getAnnotation(Table.class);
		String fullTableNameWithSchema = table.schema() + "." + table.name();
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createNativeQuery(READ_ALL_QUERY + fullTableNameWithSchema, classType);
		List<T> queryResultList = query.getResultList();
		return queryResultList.stream()
				.map(Optional::ofNullable)
				.collect(Collectors.toList());
	}
	
	@Loggable
	@Override
	public Optional<T> read (final Class<T> classType, final Long id) {
		Session session = sessionFactory.getCurrentSession();
		return Optional.ofNullable(session.get(classType, id));
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
