package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.repository.IRepository;

import java.util.List;
import java.util.Optional;

public class AbstractRepository <T extends Entity, K>  implements IRepository<T, K> {
	@Override
	public void create (T entity) {
		
	}
	
	@Override
	public List<Optional<T>> readAll () {
		return null;
	}
	
	@Override
	public Optional<T> read (K id) {
		return null;
	}
	
	@Override
	public void update (T entity) {
		
	}
	
	@Override
	public void delete (T entity) {
		
	}
}
