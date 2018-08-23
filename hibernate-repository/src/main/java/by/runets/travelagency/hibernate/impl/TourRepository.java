package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.ITourRepository;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class TourRepository extends AbstractRepository<Tour> implements ITourRepository<Tour, Long> {

	private static final String FIND_TOUR_BY_COUNTRY_ID_AND_DURATION = "";
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public TourRepository (SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Optional<Tour>> findTourByCountryAndDuration (Long countryId, LocalDate startTourDate, LocalDate endTourDate) {
		return null;
	}
}
