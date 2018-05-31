package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.repository.IRepository;
import by.runets.travelagency.service.IService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is a common class which implements common CRUD interface and provides default method implementing.
 * @param <T> is a generic param which must be inherited from Entity class.
 * @param <K> is a generic param which represents a key param.
 */
@AllArgsConstructor
public class AbstractService<T extends Entity, K> implements IService<T, K> {
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
	private final IRepository<T, K> repository;
	
	/**
	 * This is a method which call create method from repository layer.
	 * @param entity  generic exemplar.
	 */
	@Override
	public void create(final T entity) {
		LOGGER.info("Create method in service layer is invoked.");
		repository.create(entity);
	}
	
	/**
	 * This is a method which returns list of entities.
	 * @return list of entities.
	 */
	@Override
	public List<T> readAll() {
		LOGGER.info("Read all method in service layer is invoked.");
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
	public T read(final K id) {
		LOGGER.info("Read entity by id method in service layer is invoked.");
		return repository
				.read(id)
				.orElseThrow(() -> new ResourceNotFoundException("The entity by id " + id + " does not exist."));
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Override
	public void update(final T entity) {
		LOGGER.info("Update entity method in service layer is invoked.");
		repository.update(entity);
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Override
	public void delete(final T entity) {
		LOGGER.info("Delete entity method in service layer is invoked.");
		repository.delete(entity);
	}
}
