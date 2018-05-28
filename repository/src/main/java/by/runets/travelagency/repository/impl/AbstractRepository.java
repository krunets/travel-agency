package by.runets.travelagency.repository.impl;

import by.runets.travelagency.entity.Entity;
import by.runets.travelagency.repository.IRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AbstractRepository<T extends Entity, K> implements IRepository<T, K> {
	private List<T> data;
	
	@Override
	public void create (T entity) {
		data.add(entity);
	}
	
	@Override
	public List<Optional<T>> readAll () {
		return data.stream()
				.map(Optional::ofNullable)
				.collect(Collectors.toList());
	}
	
	@Override
	public Optional<T> read (K id) {
		return data.stream()
				.filter(entity -> entity.getId() == id)
				.findFirst();
	}
	
	@Override
	public void update (T entity) {
		Optional<T> optional = Optional.ofNullable(entity);
		int index[] = {0};
		optional.ifPresent(checkedEntity -> {
			data.forEach(e -> {
				if (e.getId() == checkedEntity.getId()) {
					data.set(index[0], checkedEntity);
				}
				index[0]++;
			});
		});
	}
	
	@Override
	public void delete (T entity) {
		Optional<T> optional = Optional.ofNullable(entity);
		optional.ifPresent(checkedEntity -> {
			data.removeIf(e -> e.getId() == entity.getId());
		});
	}
}
