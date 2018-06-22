package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.IDatabaseRepository;

public class TourService extends AbstractService<Tour, Integer> {
	public TourService (IDatabaseRepository<Tour, Integer> repository) {
		super(repository);
	}
}
