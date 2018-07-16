package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.hibernate.IDatabaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractService<Review, Integer> {
	public ReviewService (IDatabaseRepository<Review, Integer> repository, Class<Review> classType) {
		super(repository, classType);
	}
}
