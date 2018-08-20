package by.runets.travelagency.service.impl;

import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import by.runets.travelagency.service.IService;
import by.runets.travelagency.util.annotation.Loggable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is a common class which implements common CRUD interface and provides default method implementing.
 * @param <T> is a generic param which must be inherited from PrimaryKeyEntity class.
 */
@Service
@AllArgsConstructor
public abstract class AbstractService<T, K> implements IService<T, K> {
	@Autowired
	private final Class<T> classType;
	@Autowired
	private final IDatabaseRepository<T, K> abstractRepository;
	
	/**
	 * This is a method which call create method from abstractRepository layer.
	 * @param entity  generic exemplar.
	 */
	@Loggable
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public K create(final T entity) {
		return abstractRepository.create(entity);
	}
	
	/**
	 * This is a method which returns list of entities.
	 * @return list of entities.
	 */
	@Loggable
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public List<T> readAll() {
		return abstractRepository
				.readAll(classType)
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
	@Override
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public T read(final K id) {
		return abstractRepository
				.read(classType, id)
				.orElseThrow(() -> new ResourceNotFoundException("The entity by id " + id + " does not exist."));
	}
	
	/**
	 * This is a method which call update method from abstractRepository.
	 * @param entity generic exemplar.
	 */
	@Loggable
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void update(final T entity) {
		abstractRepository.update(entity);
	}
	
	/**
	 * This is a method which call update method from abstractRepository.
	 * @param entity generic exemplar.
	 */
	@Loggable
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void delete(final T entity) {
		abstractRepository.delete(entity);
	}
}
