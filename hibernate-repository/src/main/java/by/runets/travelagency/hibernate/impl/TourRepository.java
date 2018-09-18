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

import static by.runets.travelagency.util.constant.NamedQueryConstant.*;

@Repository
public class TourRepository extends AbstractRepository<Tour> implements ITourRepository {
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
		
		query.setParameter(COUNTRY_NAME_FIELD, countryName);
		query.setParameter(DATE_FIELD, startTourDate);
		query.setParameter(DURATION_FIELD, tourDuration);
		
		List<Tour> queryResultList = query.getResultList();
		return queryResultList.stream()
				.map(Optional::ofNullable)
				.collect(Collectors.toList());
	}
}
