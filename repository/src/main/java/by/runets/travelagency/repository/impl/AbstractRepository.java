package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
/** Common class which implements common CRUD interface and provides default methods implementing. */
public class AbstractRepository<T extends Entity, K> implements IRepository<T, K> {
	private List<T> data;
	
	/**
	 * This is a method which add entity to common collection.
	 *
	 * @param entity generic exemplar.
	 */
	@Override
	public void create(T entity) {
		data.add(entity);
	}
	
	/**
	 * This is a method which return all entities from collection.
	 *
	 * @return list of entities.
	 */
	@Override
	public List<Optional<T>> readAll() {
		return data.stream().map(Optional::ofNullable).collect(Collectors.toList());
	}
	
	/**
	 * This is a method which return entity by id.
	 *
	 * @param id is a generic param which represents a key param.
	 * @return entity from collection.
	 */
	@Override
	public Optional<T> read(K id) {
		return data.stream().filter(entity -> entity.getId() == id).findFirst();
	}
	
	/**
	 * This is a method which update entity in collection if id of such entity is exist.
	 * @param entity entity generic exemplar
	 */
	@Override
	public void update(T entity) {
		Optional<T> optional = Optional.ofNullable(entity);
		int index[] = {0};
		optional.ifPresent(
				checkedEntity -> {
					data.forEach(
							e -> {
								if (e.getId() == checkedEntity.getId()) {
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
	public void delete(T entity) {
		Optional<T> optional = Optional.ofNullable(entity);
		optional.ifPresent(
				checkedEntity -> {
					data.removeIf(e -> e.getId() == entity.getId());
				});
	}
}
