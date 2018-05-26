package by.runets.travelagency.repository;

import by.runets.travelagency.entity.Entity;

import java.util.List;
import java.util.Optional;

public interface IRepository <T extends Entity, K> {
	void create(T entity);
	List<Optional<T>> readAll();
	Optional<T> read(K id);
	void update(T entity);
	void delete(T entity);
}