package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class TourService extends AbstractService<Tour, Integer> {
	public TourService (IDatabaseRepository<Tour, Integer> repository) {
		super(repository);
	}
}
