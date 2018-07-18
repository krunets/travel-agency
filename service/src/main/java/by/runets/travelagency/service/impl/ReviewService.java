package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractService<Review, Long> {
	
	public ReviewService (Class<Review> classType, IDatabaseRepository<Review, Long> abstractRepository) {
		super(classType, abstractRepository);
	}
}
