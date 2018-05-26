package by.runets.travelagency.service;

import by.runets.travelagency.entity.Entity;

import java.util.List;

public interface IService <T extends Entity, K> {
	void create(T entity);
	List<T> readAll();
	T read(K id);
	void update(T entity);
	void delete(T entity);
}