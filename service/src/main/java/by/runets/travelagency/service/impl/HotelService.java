package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.repository.ICollectionRepository;

public class HotelService extends AbstractService<Hotel, Integer> {
	public HotelService (ICollectionRepository<Hotel, Integer> repository) {
		super(repository);
	}
}
