package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.Tour;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TourRepository extends AbstractRepository<Tour> {
	public TourRepository (SessionFactory sessionFactory) {
		super(sessionFactory);
	}
}
