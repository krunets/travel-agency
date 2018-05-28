package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.repository.IRepository;

public class ReviewService extends AbstractService<Review, Integer> {
	public ReviewService (IRepository<Review, Integer> repository) {
		super(repository);
	}
}
