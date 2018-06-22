package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.repository.IDatabaseRepository;

public class ReviewService extends AbstractService<Review, Integer> {
	public ReviewService (IDatabaseRepository<Review, Integer> repository) {
		super(repository);
	}
}
