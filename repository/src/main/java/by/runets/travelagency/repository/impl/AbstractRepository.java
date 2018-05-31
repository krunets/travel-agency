package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Common class which implements common CRUD interface and provides default methods implementing.
 * @param <T> is a generic param which must be inherited from Entity class.
 * @param <K> is a generic param which represents a key param.
 */
@AllArgsConstructor
public class AbstractRepository<T extends Entity, K> implements IRepository<T, K> {
	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);
	private List<T> data;
	
	/**
	 * This is a method which add entity to common collection.
	 *
	 * @param entity generic exemplar.
	 */
	@Override
	public void create(final T entity) {
		if (data.add(entity)) {
			LOGGER.info("The entity " + entity + " added to collection.");
		}
	}
	
	/**
	 * This is a method which return all entities from collection.
	 *
	 * @return list of entities.
	 */
	@Override
	public List<Optional<T>> readAll() {
		LOGGER.info("Read all method invoke");
		return data.stream().map(Optional::ofNullable).collect(Collectors.toList());
	}
	
	/**
	 * This is a method which return entity by id.
	 *
	 * @param id is a generic param which represents a key param.
	 * @return entity from collection.
	 */
	@Override
	public Optional<T> read(final K id) {
		LOGGER.info("Read entity by id from collection method invoke");
		return data.stream().filter(entity -> {
			boolean state = entity.getId() == id;
			if (state) {
				LOGGER.info("Find entity " + entity + " by id " + id);
			}
			return state;
		}).findFirst();
	}
	
	/**
	 * This is a method which update entity in collection if id of such entity is exist.
	 * @param entity entity generic exemplar
	 */
	@Override
	public void update(final T entity) {
		LOGGER.info("Update entity in collection method invoke");
		Optional<T> optional = Optional.ofNullable(entity);
		int index[] = {0};
		optional.ifPresent(
				checkedEntity -> {
					data.forEach(
							e -> {
								if (e.getId() == checkedEntity.getId()) {
									LOGGER.info("Object " + e + " is updating to " + checkedEntity);
									data.set(index[0], checkedEntity);
								}
								index[0]++;
							});
				});
	}
	
	/**
	 * This is a method which delete entity from collection.
	 * @param entity generic exemplar.
	 */
	@Override
	public void delete(final T entity) {
		LOGGER.info("Delete entity from collection method invoke");
		Optional<T> optional = Optional.ofNullable(entity);
		optional.ifPresent(
				checkedEntity -> {
					data.removeIf(e -> {
						boolean state = e.getId() == entity.getId();
						if (state) {
							LOGGER.info("The entity " + entity + " removed from collection.");
						}
						return state;
					});
				});
	}
}
