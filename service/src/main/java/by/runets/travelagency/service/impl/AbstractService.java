package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.repository.IDatabaseRepository;
import by.runets.travelagency.service.IService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is a common class which implements common CRUD interface and provides default method implementing.
 * @param <T> is a generic param which must be inherited from Entity class.
 * @param <K>  is a generic param which represents a key param.
 */
@Service
@Slf4j
@AllArgsConstructor
public class AbstractService<T extends Entity, K> implements IService<T, K> {
	@Autowired
	private final IDatabaseRepository<T, K> repository;
	
	/**
	 * This is a method which call create method from repository layer.
	 * @param entity  generic exemplar.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void create(final T entity) {
		log.info("Create method in service layer is invoked.");
		repository.create(entity);
	}
	
	/**
	 * This is a method which returns list of entities.
	 * @return list of entities.
	 */
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	@Override
	public List<T> readAll() {
		log.info("Read all method in service layer is invoked.");
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
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	@Override
	public T read(final K id) {
		log.info("Read entity by id method in service layer is invoked.");
		return repository
				.read(id)
				.orElseThrow(() -> new ResourceNotFoundException("The entity by id " + id + " does not exist."));
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void update(final T entity) {
		log.info("Update entity method in service layer is invoked.");
		repository.update(entity);
	}
	
	/**
	 * This is a method which call update method from repository.
	 * @param entity generic exemplar.
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	@Override
	public void delete(final T entity) {
		log.info("Delete entity method in service layer is invoked.");
		repository.delete(entity);
	}
}
