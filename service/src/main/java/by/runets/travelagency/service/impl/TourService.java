package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.IRepository;

public class TourService extends AbstractService<Tour, Integer> {
	public TourService (IRepository<Tour, Integer> repository) {
		super(repository);
	}
}
