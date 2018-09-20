package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.Country;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CountryRepository extends AbstractRepository<Country> {
  public CountryRepository(SessionFactory sessionFactory) {
	super(sessionFactory);
  }
}
