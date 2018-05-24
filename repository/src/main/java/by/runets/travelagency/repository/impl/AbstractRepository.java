package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class AbstractRepository<T extends Entity, K> implements IRepository<T, K> {
	private List<T> data;
	
	@Override
	public void create (T entity) {
		data.add(entity);
	}
	
	@Override
	public Optional<List<T>> readAll () {
		return Optional.of(data);
	}
	
	@Override
	public Optional<T> read (K id) {
		return data.stream()
				.filter(entity -> entity.getId() == id)
				.findFirst();
	}
	
	@Override
	public void update (T entity) {
		data.stream()
				.filter(e -> e.getId() == entity.getId())
				.map(e -> e == entity);
	}
	
	@Override
	public void delete (T entity) {
		data.removeIf(e -> e.getId() == entity.getId());
	}
}
