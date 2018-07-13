package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.Review;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepository extends AbstractRepository<Review> {
	public ReviewRepository (SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
