package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.repository.IRepository;
import by.runets.travelagency.repository.impl.AbstractRepository;
import by.runets.travelagency.service.IService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
/**
 * This is a common class which implements common CRUD interface and provides default method implementing.
 */
public class AbstractService<T extends Entity, K> implements IService<T, K> {
	private final IRepository<T, K> repository;
	
	/**
	 * This is a method which call create method from repository layer.
	 * @param entity  generic exemplar.
	 */
	@Override
	public void create(T entity) {
		repository.create(entity);
	}
	
	/**
	 * This is a method which returns list of entities.
	 * @return list of entities.
	 */
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
	@Override
	public T read(K id) {
		return repository
				.read(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("The entity by id " + id + " does not exist."));
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Override
	public void update(T entity) {
		repository.update(entity);
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Override
	public void delete(T entity) {
		repository.delete(entity);
	}
}
