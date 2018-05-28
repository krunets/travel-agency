package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.exception.ResourceNotFoundException;
import by.runets.travelagency.repository.IRepository;
import by.runets.travelagency.repository.impl.AbstractRepository;
import by.runets.travelagency.service.IService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AbstractService <T extends Entity, K> implements IService<T, K> {
	private final IRepository<T, K> repository;
	
	@Override
	public void create (T entity) {
		repository.create(entity);
	}
	
	@Override
	public List<T> readAll () {
		return repository.readAll()
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}
	
	@Override
	public T read (K id) {
		return repository.read(id)
				.orElseThrow(() -> new ResourceNotFoundException("The entity by id " + id + " does not exist."));
	}
	
	@Override
	public void update (T entity) {
		repository.update(entity);
	}
	
	@Override
	public void delete (T entity) {
		repository.delete(entity);
	}
}
