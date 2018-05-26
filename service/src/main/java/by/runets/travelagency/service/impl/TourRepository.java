package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.repository.impl.AbstractRepository;

public class TourRepository extends AbstractService<Tour<Integer>, Integer> {
	public TourRepository (AbstractRepository<Tour<Integer>, Integer> repository) {
		super(repository);
	}
}
