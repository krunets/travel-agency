package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class TourService extends AbstractService<Tour, Long> {
	
	public TourService (Class<Tour> classType, IDatabaseRepository<Tour, Long> abstractRepository) {
		super(classType, abstractRepository);
	}
}
