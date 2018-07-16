package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.impl.TourRepository;
import org.springframework.stereotype.Service;

@Service
public class TourService extends AbstractService<Tour> {
	public TourService (TourRepository tourRepository, Class<Tour> classType) {
		super(tourRepository, classType);
	}
}
