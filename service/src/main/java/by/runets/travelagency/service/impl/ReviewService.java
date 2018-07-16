package by.runets.travelagency.service.impl;

import by.runets.travelagency.entity.Review;
import by.runets.travelagency.hibernate.impl.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends AbstractService<Review> {
	public ReviewService (ReviewRepository reviewRepository, Class<Review> classType) {
		super(reviewRepository, classType);
	}
}
