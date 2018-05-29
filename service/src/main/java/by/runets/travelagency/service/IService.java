package by.runets.travelagency.service;

import by.runets.travelagency.entity.Entity;

import java.util.List;

/**
 * This is an interface which provides CRUD methods in service layer.
 * @param <T> is a generic param which must be inherited from Entity class.
 * @param <K> is a generic param which represents a key param.
 */
public interface IService <T extends Entity, K> {
	void create(T entity);
	List<T> readAll();
	T read(K id);
	void update(T entity);
	void delete(T entity);
}