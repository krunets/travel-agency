package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.repository.impl.AbstractRepository;

public class ReviewService extends AbstractService<Review<Integer>, Integer> {
	public ReviewService (AbstractRepository<Review<Integer>, Integer> repository) {
		super(repository);
	}
}
