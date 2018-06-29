package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.repository.IDatabaseRepository;
import by.runets.travelagency.service.IService;
import by.runets.travelagency.util.annotation.Loggable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is a common class which implements common CRUD interface and provides default method implementing.
 * @param <T> is a generic param which must be inherited from Entity class.
 * @param <K>  is a generic param which represents a key param.
 */
@Loggable
@AllArgsConstructor
public class AbstractService<T extends Entity, K> implements IService<T, K> {
	@Autowired
	private final IDatabaseRepository<T, K> repository;
	
	/**
	 * This is a method which call create method from repository layer.
	 * @param entity  generic exemplar.
	 */
	@Loggable
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void create(final T entity) {
		repository.create(entity);
	}
	
	/**
	 * This is a method which returns list of entities.
	 * @return list of entities.
	 */
	@Loggable
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	@Override
	public List<T> readAll() {
		return repository
				.readAll()
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	
	/**
	 * This is a method which return entity by id.
	 * @param id is a generic param which represents a key param.
	 * @return entity.
	 */
	@Loggable
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	@Override
	public T read(final K id) {
		return repository
				.read(id)
				.orElseThrow(() -> new ResourceNotFoundException("The entity by id " + id + " does not exist."));
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Loggable
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void update(final T entity) {
		repository.update(entity);
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Loggable
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void delete(final T entity) {
		repository.delete(entity);
	}
}
