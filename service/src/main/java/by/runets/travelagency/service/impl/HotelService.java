package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.repository.IRepository;

public class HotelService extends AbstractService<Hotel, Integer> {
	public HotelService (IRepository<Hotel, Integer> repository) {
		super(repository);
	}
}
