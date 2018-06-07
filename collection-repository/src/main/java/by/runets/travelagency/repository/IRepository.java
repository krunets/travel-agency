package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Entity;

import java.util.List;
import java.util.Optional;

/** Common interface which provides CRUD methods in repository layer.
 * @param <T> is a generic param which must be inherited from Entity class.
 * @param <K> is a generic param which represents a key param.
 */
public interface IRepository<T extends Entity, K> {
	void create(final T entity);
	List<Optional<T>> readAll();
	Optional<T> read(final K id);
	void update(final T entity);
	void delete(final T entity);
}