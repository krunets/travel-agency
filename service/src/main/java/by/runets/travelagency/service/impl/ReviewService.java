package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.repository.ICollectionRepository;

public class ReviewService extends AbstractService<Review, Integer> {
	public ReviewService (ICollectionRepository<Review, Integer> repository) {
		super(repository);
	}
}
