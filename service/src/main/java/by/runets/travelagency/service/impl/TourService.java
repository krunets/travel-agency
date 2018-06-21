package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.ICollectionRepository;

public class TourService extends AbstractService<Tour, Integer> {
	public TourService (ICollectionRepository<Tour, Integer> repository) {
		super(repository);
	}
}
