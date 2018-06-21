package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Entity;

import java.util.List;
import java.util.Optional;

/**
 * Common interface which provides CRUD methods in repository layer.
 *
 * @param <T> is a generic param which must be inherited from Entity class.
 * @param <K> is a generic param which represents a key param.
 */
@Deprecated
public interface ICollectionRepository<T extends Entity, K> {
	@Deprecated
	void create (final T entity);
	@Deprecated
	List<Optional<T>> readAll ();
	@Deprecated
	Optional<T> read (final K id);
	@Deprecated
	void update (final T entity);
	@Deprecated
	void delete (final T entity);
}