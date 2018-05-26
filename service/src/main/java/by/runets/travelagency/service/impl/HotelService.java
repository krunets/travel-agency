package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.repository.impl.AbstractRepository;

public class HotelService extends AbstractService<Hotel<Integer>, Integer> {
	public HotelService (AbstractRepository<Hotel<Integer>, Integer> repository) {
		super(repository);
	}
}
