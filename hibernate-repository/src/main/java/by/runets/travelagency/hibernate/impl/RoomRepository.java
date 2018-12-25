package by.runets.travelagency.hibernate.impl;

import by.runets.travelagency.entity.Room;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository extends AbstractRepository<Room> {
  public RoomRepository(SessionFactory sessionFactory) {
	super(sessionFactory);
  }
}
