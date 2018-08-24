package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.Tour;
import by.runets.travelagency.hibernate.ITourRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TourRepository extends AbstractRepository<Tour> implements ITourRepository<Tour, Long> {
	
	private static final String FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION = "FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION";
	private static final String COUNTRY_NAME = "countryName";
	private static final String DATE = "date";
	private static final String DURATION = "duration";
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public TourRepository (SessionFactory sessionFactory) {
		super(sessionFactory);
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Optional<Tour>> findTourByCountryAndDateAndDuration (final String countryName, final LocalDate startTourDate, final Duration tourDuration) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(FIND_TOUR_BY_COUNTRY_AND_DATE_AND_DURATION);
		
		query.setParameter(COUNTRY_NAME, countryName);
		query.setParameter(DATE, startTourDate);
		query.setParameter(DURATION, tourDuration);

		List<Tour> queryResultList = query.getResultList();
		return queryResultList.stream()
				.map(Optional::ofNullable)
				.collect(Collectors.toList());
	}
}
