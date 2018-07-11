package by.runets.travelagency.service;

import java.util.List;

/**
 * This is an interface which provides CRUD methods in service layer.
 * @param <T> is a generic param which must be inherited from PrimaryKeyEntity class.
 * @param <K> is a generic param which represents a key param.
 */
public interface IService <T extends PrimaryKeyEntity, K> {
	void create(final T entity);
	List<T> readAll();
	T read(final K id);
	void update(final T entity);
	void delete(final T entity);
}