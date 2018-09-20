package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.Hotel;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HotelRepository extends AbstractRepository<Hotel> {
  public HotelRepository(SessionFactory sessionFactory) {
	super(sessionFactory);
  }
}
