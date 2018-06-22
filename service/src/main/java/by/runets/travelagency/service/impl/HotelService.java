package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Hotel;
import by.runets.travelagency.repository.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends AbstractService<Hotel, Integer> {
	public HotelService (IDatabaseRepository<Hotel, Integer> repository) {
		super(repository);
	}
}
